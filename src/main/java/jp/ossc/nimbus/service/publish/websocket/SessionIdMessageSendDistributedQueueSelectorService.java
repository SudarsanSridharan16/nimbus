package jp.ossc.nimbus.service.publish.websocket;

import java.util.Set;

import jp.ossc.nimbus.service.publish.websocket.AbstractPublishMessageDispatcherService.MessageSendParameter;
import jp.ossc.nimbus.service.queue.AbstractDistributedQueueSelectorService;

/**
 * ���b�Z�[�W�𑗐M���邽�߂�DistributeQueueHandlerContainer��Selector�T�[�r�X�B
 *
 * @author M.Ishida
 *
 */
public class SessionIdMessageSendDistributedQueueSelectorService extends AbstractDistributedQueueSelectorService
        implements SessionIdMessageSendDistributedQueueSelectorServiceMBean {

    protected Object getKey(Object obj) {
        MessageSendParameter param = (MessageSendParameter) obj;
        return param.getSender().getSession().getId();
    }

    public void remove(String sessionId) {
        synchronized (keyMap) {
            keyMap.remove(sessionId);
            for (int i = 0; i < keySets.length; i++) {
                Set keySet = keySets[i];
                keySet.remove(sessionId);
            }
        }
    }
}