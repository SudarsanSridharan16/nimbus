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
package jp.ossc.nimbus.service.cache;

import java.util.*;

import jp.ossc.nimbus.core.ServiceBase;

/**
 * �L���b�V���L�����Ԃ��ӂꌟ�؃T�[�r�X�B<p>
 * �L���b�V���L�����Ԃł��ӂ�����؂���OverflowValidator�ł���B<br>
 * �ȉ��ɁA�L���b�V�����Ă���10�b�𒴂���Ƃ��ӂ�邠�ӂꌟ�؃T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="TimeExpierOverflowValidator"
 *                  code="jp.ossc.nimbus.service.cache.TimeExpierOverflowValidatorService"&gt;
 *             &lt;attribute name="ExpierTerm"&gt;10000&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class TimeExpierOverflowValidatorService extends ServiceBase
 implements OverflowValidator, CacheRemoveListener, java.io.Serializable,
            TimeExpierOverflowValidatorServiceMBean{
    
    private static final long serialVersionUID = -5221705956555820688L;
    
    /**
     * �L���b�V���L�����ԁB<p>
     */
    private long expierTerm = -1;
    
    /**
     * �L���b�V���L����؂�
     */
    private long period = -1;

    /**
     * �L���b�V���Q�ƂƓo�^���Ԃ̏W���B<p>
     */
    private Map references;
    
    // TimeExpierOverflowValidatorServiceMBean��JavaDoc
    public int size(){
        return references == null ? 0 : references.size();
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B<br>
     * 
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        references = Collections.synchronizedMap(new LinkedHashMap());
    }

    /**
     * �T�[�r�X���J�n����B<p>
     * ���̃T�[�r�X�𗘗p�\�ȏ�Ԃɂ���B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception {
    }

    /**
     * �T�[�r�X�̔j���������s���B<p>
     * {@link #reset()}���Ăяo���B�܂��A�C���X�^���X�ϐ��̎Q�Ƃ�j������B<br>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        reset();
        references = null;
    }
    
    // TimeExpierOverflowValidatorServiceMBean��JavaDoc
    public void setExpierTerm(long millis) throws IllegalArgumentException{
        expierTerm = millis;
    }
    
    // TimeExpierOverflowValidatorServiceMBean��JavaDoc
    public long getExpierTerm(){
        return expierTerm;
    }
    
    // TimeExpierOverflowValidatorServiceMBean��JavaDoc
    public void setPeriod(long millis) throws IllegalArgumentException {
        period = millis;
    }

    // TimeExpierOverflowValidatorServiceMBean��JavaDoc
    public long getPeriod() {
        return period;
    }

    /**
     * �L���b�V�����L�����Ԃ��߂��Ă��邩���؂���B<p>
     *
     * @return �L���b�V�����L�����Ԃ��߂��Ă���ꍇ�A�߂��Ă���L���b�V���̐���Ԃ��B�߂��Ă��Ȃ��ꍇ�́A0��Ԃ�
     */
    public int validate(){
        if(references == null || references.size() == 0){
            return 0;
        }
        synchronized(references){
            if(getState() != STARTED){
                return 0;
            }
            final long currentTime = System.currentTimeMillis();
            if(getExpierTerm() < 0
                && period <= 0
            ){
                return 0;
            }
            final Object[] dates = references.values().toArray();

            boolean validateExpierTime = true;
            if (expierTerm < 0) {
                // �L�����Ԃ̌��؂͍s��Ȃ�
                validateExpierTime = false;
            }
            boolean validatePeriod = true;
            if (period <= 0) {
                // �L����؂�̌��؂͍s��Ȃ�
                validatePeriod = false;
            }

            int count = 0;
            for(; count < dates.length; count++){
                // �L���b�V���o�^����[ms]
                long refMillis = ((Date)dates[count]).getTime();
  
                // �L������
                if(validateExpierTime
                    && currentTime < refMillis + expierTerm){
                    validateExpierTime = false;
                }
                
                // �L����؂�
                if(validatePeriod
                    && currentTime < refMillis + (period - (refMillis - 9 * 60 * 60 * 1000) % period)){
                    validatePeriod = false;
                }
                
                if (!validateExpierTime
                    && !validatePeriod) {
                    break;
                }
            }

            return count;
        }
    }

    // OverflowValidator��JavaDoc
    public void add(CachedReference ref){
        if(references == null || ref == null){
            return;
        }
        synchronized(references){
            if(!references.containsKey(ref)){
                references.put(ref, new Date());
                ref.addCacheRemoveListener(this);
            }
        }
    }
    
    // OverflowValidator��JavaDoc
    public void remove(CachedReference ref){
        if(references == null || ref == null){
            return;
        }
        synchronized(references){
            if(references.containsKey(ref)){
                references.remove(ref);
                ref.removeCacheRemoveListener(this);
            }
        }
    }
    
    // OverflowValidator��JavaDoc
    public void reset(){
        if(references != null){
            synchronized(references){
                final Object[] refs = references.keySet().toArray();
                for(int i = 0; i < refs.length; i++){
                    final CachedReference ref = (CachedReference)refs[i];
                    remove(ref);
                }
                references.clear();
            }
        }
    }
    
    /**
     * {@link #add(CachedReference)}�Œǉ����ꂽ{@link CachedReference}�̃L���b�V���I�u�W�F�N�g���폜���ꂽ�ꍇ�ɌĂяo�����B<p>
     * �폜���ꂽ�L���b�V���Q�Ƃ�{@link #remove(CachedReference)}�ŁA����OverflowValidator������폜����B<br>
     *
     * @param ref �폜���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     */
    public void removed(CachedReference ref){
        if(references == null){
            return;
        }
        remove(ref);
    }
}
