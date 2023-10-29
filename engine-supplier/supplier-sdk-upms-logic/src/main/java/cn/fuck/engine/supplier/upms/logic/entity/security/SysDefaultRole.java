package cn.fuck.engine.supplier.upms.logic.entity.security;

import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.supplier.upms.logic.constants.UpmsConstants;
import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 系统默认角色 </p>
 * @date : 2021/7/16 17:01
 */
@Entity
@Table(name = "sys_default_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"default_id", "scene"})},
        indexes = {
                @Index(name = "sys_default_role_did_idx", columnList = "default_id"),
                @Index(name = "sys_default_role_rid_idx", columnList = "role_id")}
)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = UpmsConstants.REGION_SYS_DEFAULT_ROLE)
public class SysDefaultRole extends BaseSysEntity {

    @Id
    @UuidGenerator
    @Column(name = "default_id", length = 64)
    private String defaultId;

    @Schema(title = "场景")
    @Column(name = "scene", unique = true)
    @Enumerated(EnumType.STRING)
    private AccountType scene = AccountType.INSTITUTION;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = UpmsConstants.REGION_SYS_ROLE)
    @Schema(title = "角色ID")
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private SysRole role;

    public String getDefaultId() {
        return defaultId;
    }

    public void setDefaultId(String defaultId) {
        this.defaultId = defaultId;
    }

    public AccountType getScene() {
        return scene;
    }

    public void setScene(AccountType scene) {
        this.scene = scene;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("defaultId", defaultId)
                .add("supplierType", scene)
                .add("role", role)
                .toString();
    }
}
