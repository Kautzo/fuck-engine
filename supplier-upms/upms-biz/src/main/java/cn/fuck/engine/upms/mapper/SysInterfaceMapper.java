package cn.fuck.engine.upms.mapper;

import cn.fuck.engine.upms.entity.SysInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SysInterfaceMapper extends BaseMapper<SysInterface> {

    List<SysInterface> getAllocatable();

}
