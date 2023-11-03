package cn.fuck.engine.oauth2.core.definition.domain;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * <p>Description: 权限对象 </p>
 */
@Data
public class FuckPermission {

    private String permissionId;

    private String permissionCode;

    private String permissionName;

}
