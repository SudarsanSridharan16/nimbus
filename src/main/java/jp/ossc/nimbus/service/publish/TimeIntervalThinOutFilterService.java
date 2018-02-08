/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 *
 * Copyright 2003 The Nimbus Project. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE NIMBUS PROJECT ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE NIMBUS PROJECT OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of the Nimbus Project.
 */
package jp.ossc.nimbus.service.publish;

import java.util.Map;
import java.util.HashMap;

import jp.ossc.nimbus.core.ServiceBase;

/**
 * ���ԊԊu�Ńt�B���^�[����{@link ThinOutFilter}�����T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class TimeIntervalThinOutFilterService extends ServiceBase
 implements ThinOutFilter, TimeIntervalThinOutFilterServiceMBean{
    
    private static final long serialVersionUID = 4026088529165612279L;
    
    private long thinOutInterval = 1000;
    private Map lastSendTimeSubjectMap;
    
    public void setThinOutInterval(long interval){
        thinOutInterval = interval;
    }
    public long getThinOutInterval(){
        return thinOutInterval;
    }
    
    public void createService() throws Exception{
        lastSendTimeSubjectMap = new HashMap();
    }
    
    public void destroyService() throws Exception{
        lastSendTimeSubjectMap = null;
    }
    
    public boolean isThinOut(Message msg){
        if(getState() != STARTED){
            return false;
        }
        Map lastSendTimeMap = (Map)lastSendTimeSubjectMap.get(msg.getSubject());
        if(lastSendTimeMap == null){
            return false;
        }
        SendTime sendTime = (SendTime)lastSendTimeMap.get(msg.getKey());
        if(sendTime == null){
            return false;
        }
        return sendTime.isThinOut();
    }
    
    public void notifySendMessage(Message msg){
        Map lastSendTimeMap = (Map)lastSendTimeSubjectMap.get(msg.getSubject());
        if(lastSendTimeMap == null){
            lastSendTimeMap = new HashMap();
            lastSendTimeSubjectMap.put(msg.getSubject(), lastSendTimeMap);
        }
        SendTime sendTime = (SendTime)lastSendTimeMap.get(msg.getKey());
        if(sendTime == null){
            sendTime = new SendTime();
            lastSendTimeMap.put(msg.getKey(), sendTime);
        }
        sendTime.send();
    }
    
    private class SendTime{
        private long time;
        public void send(){
            time = System.currentTimeMillis();
        }
        public boolean isThinOut(){
            return thinOutInterval > (System.currentTimeMillis() - time);
        }
    }
}