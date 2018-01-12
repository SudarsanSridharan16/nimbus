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
package jp.ossc.nimbus.service.context;

import java.util.*;
import jp.ossc.nimbus.core.*;
//
/**
 * �X���b�h�R���e�L�X�g�T�[�r�X�B<p>
 * �X���b�h���̃R���e�L�X�g�ŃL�[�A�l���Ǘ��ł���B<br>
 * �܂��A�q�X���b�h����̒l�̎Q�Ƃ��\�ł���B<br>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="Context"
 *                  code="jp.ossc.nimbus.service.context.ThreadContextService"&gt;
 *             &lt;attribute name="HOME_PATH"&gt;/home&lt;/attribute&gt;
 *             &lt;attribute name="DOMAIN"&gt;nimbus.ossc.jp&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author H.Nakano
 */
public class ThreadContextService extends ServiceBase
 implements Context, ThreadContextServiceMBean{
    
    private static final long serialVersionUID = -7304455455493489289L;
    
    /**
     * �R���e�L�X�g�i�[�p�X���b�h���[�J���B<p>
     */
    protected ThreadLocal threadLocal;
    
    /**
     * �f�t�H���g�l�i�[�p�̃}�b�v�B<p>
     */
    protected Map defaultMap;
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B<br>
     * 
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        defaultMap = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * 
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(threadLocal == null){
            clearAllThreadContext();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     * 
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        clearAllThreadContext();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * �C���X�^���X�ϐ��̔j�����s���B<br>
     * 
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        defaultMap = null;
    }
    
    // Context��JavaDoc
    public void clear(){
        Map map = (Map)threadLocal.get();
        init(map);
    }
    
    private void init(Map map){
        if(map != null){
            map.clear();
            map.putAll(defaultMap);
            final Iterator entries = map.entrySet().iterator();
            while(entries.hasNext()){
                final Map.Entry entry = (Map.Entry)entries.next();
                final Object val = entry.getValue();
                if(val == null){
                    continue;
                }else if(val instanceof ServiceName){
                    final ServiceName name = (ServiceName)val;
                    Object service = null;
                    try{
                        service = ServiceManagerFactory.getServiceObject(name);
                    }catch(ServiceNotFoundException e){
                    }
                    entry.setValue(service);
                }else if(val instanceof ServiceName[]){
                    final ServiceName[] names = (ServiceName[])val;
                    final Object[] services = new Object[names.length];
                    for(int i = 0; i < names.length; i++){
                        try{
                            services[i] = ServiceManagerFactory
                                .getServiceObject(names[i]);
                        }catch(ServiceNotFoundException e){
                        }
                    }
                    entry.setValue(services);
                }
            }
        }
    }
    
    // Context��JavaDoc
    public int size(){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.size();
        }else{
            return 0;
        }
    }
    
    // Context��JavaDoc
    public boolean isEmpty(){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.isEmpty();
        }else{
            return false;
        }
    }
    
    // Context��JavaDoc
    public boolean containsKey(Object key){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.containsKey(key);
        }else{
            return false;
        }
    }
    
    // Context��JavaDoc
    public boolean containsValue(Object value){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.containsValue(value);
        }else{
            return false;
        }
    }
    
    // Context��JavaDoc
    public Set entrySet(){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.entrySet();
        }else{
            return new HashSet();
        }
    }
    
    // Context��JavaDoc
    public Object get(Object key){
        Map map = (Map)threadLocal.get();
        return map.get(key);
    }
    
    // Context��JavaDoc
    public Object put(Object key, Object value){
        if(threadLocal == null){
            return defaultMap.put(key, value);
        }
        Map map = (Map)threadLocal.get();
        return map.put(key,value);
    }
    
    // Context��JavaDoc
    public Object remove(Object key){
        Map map = (Map)threadLocal.get();
        if(map != null){
            return map.remove(key);
        }else{
            return null;
        }
    }
    
    // Context��JavaDoc
    public void putAll(Map t){
        Map map = (Map)threadLocal.get();
        map.putAll(t);
    }
    
    // Context��JavaDoc
    public Set keySet(){
        Map map = (Map)threadLocal.get();
        return map.keySet();
    }
    
    // Context��JavaDoc
    public Collection values(){
        Map map = (Map)threadLocal.get();
        return map.values();
    }
    
    // Context��JavaDoc
    public Map all(){
        Map map = (Map)threadLocal.get();
        return new HashMap(map);
    }
    
    public Map getDefaultMap(){
        return defaultMap;
    }
    
    // ThreadContextServiceMBean��JavaDoc
    public void clearAllThreadContext(){
        threadLocal = new ThreadLocal(){
            protected synchronized Object initialValue(){
                final Map map = Collections.synchronizedMap(new HashMap());
                init(map);
                return map;
            }
        };
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @exception UnsupportedOperationException �K��throw����
     */
    public void load() throws Exception{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @exception UnsupportedOperationException �K��throw����
     */
    public void loadKey() throws Exception{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param key �L�[
     * @exception UnsupportedOperationException �K��throw����
     */
    public void load(Object key) throws Exception{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @exception UnsupportedOperationException �K��throw����
     */
    public void save() throws Exception{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param key �L�[
     * @exception UnsupportedOperationException �K��throw����
     */
    public void save(Object key) throws Exception{
        throw new UnsupportedOperationException();
    }
}
