package cn.fuck.engine.oauth2.core.definition.domain;

import cn.fuck.engine.oauth2.core.jackson2.FuckUserDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@JsonDeserialize(using = FuckUserDeserializer.class)
public class FuckUser implements UserDetails, CredentialsContainer {

    private String userId;

    private String password;

    private String username;

    private Set<GrantedAuthority> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
    public FuckUser(String userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(userId, username, password, authorities, null);
    }

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
    public FuckUser(String userId, String username, String password, Collection<? extends GrantedAuthority> authorities, Set<String> roles) {
        this(userId, username, password, true, true, true, true, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * <code>org.springframework.security.authentication.dao.DaoAuthenticationProvider</code>
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not
     *                              expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authorities           the authorities that should be granted to the caller if they
     *                              presented the correct username and password and the user is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public FuckUser(String userId, String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
        Assert.isTrue(username != null && !"".equals(username) && password != null,
                "Cannot pass null or empty values to constructor");
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new FuckUser.AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    /**
     * Returns {@code true} if the supplied object is a {@code User} instance with the
     * same {@code username} value.
     * <p>
     * In other words, the objects are equal if they have the same username, representing
     * the same principal.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FuckUser that = (FuckUser) o;
        return Objects.equal(userId, that.userId) && Objects.equal(username, that.username);
    }

    /**
     * Returns the hashcode of the {@code username}.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(userId, username);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("password", "[PROTECTED]")
                .add("username", username)
                .add("accountNonExpired", accountNonExpired)
                .add("accountNonLocked", accountNonLocked)
                .add("credentialsNonExpired", credentialsNonExpired)
                .add("enabled", enabled)
                .toString();
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set. If the authority is null, it is a custom authority and should
            // precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }
}
