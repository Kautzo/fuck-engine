package cn.fuck.engine.supplier.message.service;

import cn.fuck.engine.supplier.message.entity.PullStamp;
import cn.fuck.engine.supplier.message.repository.PullStampRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>Description: MessagePullStampService </p>
 * @date : 2022/12/7 22:10
 */
@Service
public class PullStampService extends BaseService<PullStamp, String> {

    private final PullStampRepository pullStampRepository;

    public PullStampService(PullStampRepository pullStampRepository) {
        this.pullStampRepository = pullStampRepository;
    }

    @Override
    public BaseRepository<PullStamp, String> getRepository() {
        return pullStampRepository;
    }

    public PullStamp findByUserId(String userId) {
        return pullStampRepository.findByUserId(userId).orElse(null);
    }

    public PullStamp getPullStamp(String userId) {

        PullStamp stamp = findByUserId(userId);
        if (ObjectUtils.isEmpty(stamp)) {
            stamp = new PullStamp();
            stamp.setUserId(userId);
        }
        stamp.setLatestPullTime(new Date());

        return this.save(stamp);
    }
}
