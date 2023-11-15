package cn.fuck.engine.upms.mapper;

import cn.fuck.engine.upms.entity.SysInterface;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysInterfaceMapper extends BaseMapper<SysInterface> {

    List<SysInterface> getAllocatable();

}
