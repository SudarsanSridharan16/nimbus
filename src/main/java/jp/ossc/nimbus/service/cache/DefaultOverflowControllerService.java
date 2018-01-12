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

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.queue.*;

/**
 * �f�t�H���g���ӂꐧ��B<p>
 * {@link OverflowValidator}�A{@link OverflowAlgorithm}�A{@link OverflowAction}�̂R����g�ɂ��āA���ӂꐧ����s��OverflowController�ł���B<br>
 * �܂��A���ӂꐧ��́A�L���b�V���̒ǉ������Ɠ���������K�v�͂Ȃ����߁A�ʃX���b�h�ł��ӂꐧ����s�������ł���悤��{@link Queue}�T�[�r�X��ݒ�ł���B<br>
 * �ȉ��ɁA�L���b�V���T�C�Y��10�𒴂���ƁAFIFO�ł��ӂ�ΏۂƂȂ�L���b�V���I�u�W�F�N�g�����肵�A�L���b�V������폜���邠�ӂꐧ��T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="OverflowController"
 *                  code="jp.ossc.nimbus.service.cache.DefaultOverflowControllerService"&gt;
 *             &lt;attribute name="OverflowValidatorServiceName"&gt;#CacheSizeOverflowValidator&lt;/attribute&gt;
 *             &lt;attribute name="OverflowAlgorithmServiceName"&gt;#FIFOOverflowAlgorithm&lt;/attribute&gt;
 *             &lt;depends&gt;CacheSizeOverflowValidator&lt;/depends&gt;
 *             &lt;depends&gt;FIFOOverflowAlgorithm&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="CacheSizeOverflowValidator"
 *                  code="jp.ossc.nimbus.service.cache.CacheSizeOverflowValidatorService"&gt;
 *             &lt;attribute name="MaxSize"&gt;10&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="FIFOOverflowAlgorithm"
 *                  code="jp.ossc.nimbus.service.cache.FIFOOverflowAlgorithmService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 * @see OverflowValidator
 * @see OverflowAlgorithm
 * @see OverflowAction
 * @see Queue
 */
public class DefaultOverflowControllerService extends ServiceBase
 implements OverflowController, DaemonRunnable,
            DefaultOverflowControllerServiceMBean{
    
    private static final long serialVersionUID = 304577650295674609L;
    
    /**
     * ���ӂꌟ�؃T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName validatorServiceName;
    
    /**
     * ���ӂꌟ�؃T�[�r�X�B<p>
     */
    protected OverflowValidator validator;
    
    /**
     * ���ӂ�A���S���Y���T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName algorithmServiceName;
    
    /**
     * ���ӂ�A���S���Y���T�[�r�X�B<p>
     */
    protected OverflowAlgorithm algorithm;
    
    /**
     * ���ӂꓮ��T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName actionServiceName;
    
    /**
     * ���ӂꓮ��T�[�r�X�B<p>
     */
    protected OverflowAction action;
    
    /**
     * �f�t�H���g�̂��ӂꓮ��T�[�r�X�B<p>
     * �f�t�H���g�̂��ӂꓮ��́A���ӂꂽ�L���b�V���I�u�W�F�N�g���폜����B<br>
     */
    protected RemoveOverflowActionService defaultAction;
    
    /**
     * ���ӂꐧ��̗v����ʃX���b�h�ŏ������邽�߂Ɉ�U�L���[�ɗ��߂邽�߂̃L���[�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName queueServiceName;
    
    /**
     * ���ӂꐧ��̗v����ʃX���b�h�ŏ������邽�߂Ɉ�U�L���[�ɗ��߂邽�߂̃L���[�T�[�r�X�B<p>
     */
    protected Queue queue;
    
    /**
     * ���ӂꐧ��̗v����ʃX���b�h�ŏ������邽�߂̃f�[�����I�u�W�F�N�g�B<p>
     */
    protected Daemon daemon;
    
    /**
     * ���ӂꐧ�䏈�����̃L���b�V���Q�Ƃɑ΂��铯������p�̃��b�N�I�u�W�F�N�g�B<p>
     */
    protected Object lock = "lock";
    
    /**
     * ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]�̑����l�B<p>
     */
    protected long periodicOverflowIntervalTime;
    
    /**
     * ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]�B<p>
     */
    protected long periodicOverflowInterval;
    
    /**
     * �L���b�V���Q�Ƃ��ǉ�����邽�тɂ��ӂꐧ����s�����ǂ����̃t���O�B<p>
     * �f�t�H���g�́Atrue�ŁA�ǉ��̂��тɂ��ӂꐧ����s���B<br>
     */
    protected boolean isOverflowByAdding = true;
    
    /**
     * ���ӂꐧ����s�����тɂ��ӂꌟ�؂��s�����ǂ����̃t���O�B<p>
     * �f�t�H���g�́Atrue�ŁA���ӂꐧ����s�����тɂ��ӂꌟ�؂��s���B<br>
     */
    protected boolean isValidateByOverflow = true;
    
    /**
     * �V�K�ǉ������L���b�V���Q�Ƃ����ӂ�Ώۂɉ����邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�ŁA�V�K�ǉ������L���b�V���Q�Ƃ͂��ӂ�Ώۂɉ����Ȃ��B<br>
     */
    protected boolean isOverflowNewAdding = false;
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setOverflowValidatorServiceName(ServiceName name){
        validatorServiceName = name;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public ServiceName getOverflowValidatorServiceName(){
        return validatorServiceName;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setOverflowAlgorithmServiceName(ServiceName name){
        algorithmServiceName = name;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public ServiceName getOverflowAlgorithmServiceName(){
        return algorithmServiceName;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setOverflowActionServiceName(ServiceName name){
        actionServiceName = name;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public ServiceName getOverflowActionServiceName(){
        return actionServiceName;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setQueueServiceName(ServiceName name){
        queueServiceName = name;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public ServiceName getQueueServiceName(){
        return queueServiceName;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setPeriodicOverflowIntervalTime(long time){
        periodicOverflowIntervalTime = time;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public long getPeriodicOverflowIntervalTime(){
        return periodicOverflowIntervalTime;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setOverflowByAdding(boolean isOverflow){
        isOverflowByAdding = isOverflow;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public boolean isOverflowByAdding(){
        return isOverflowByAdding;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setValidateByOverflow(boolean isValidate){
        isValidateByOverflow = isValidate;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public boolean isValidateByOverflow(){
        return isValidateByOverflow;
    }
    
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public void setOverflowNewAdding(boolean isOverflow){
        isOverflowNewAdding = isOverflow;
    }
    // DefaultOverflowControllerServiceMBean��JavaDoc
    public boolean isOverflowNewAdding(){
        return isOverflowNewAdding;
    }
    
    /**
     * OverflowAction��ݒ肷��B
     */
    public void setOverflowAction(OverflowAction action) {
        this.action = action;
    }
    /**
     * OverflowAlgorithm��ݒ肷��B
     */
    public void setOverflowAlgorithm(OverflowAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    /**
     * Queue��ݒ肷��B
     */
    public void setQueue(Queue queue) {
        this.queue = queue;
    }
    /**
     * OverflowValidator��ݒ肷��B
     */
    public void setOverflowValidator(OverflowValidator validator) {
        this.validator = validator;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �f�[�����𐶐�����B<br>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        daemon = new Daemon(this);
        daemon.setName("Nimbus OverflowControllerDaemon " + getServiceNameObject());
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * ���ӂꌟ�؃T�[�r�X���擾����B<br>
     * ���ӂ�A���S���Y���T�[�r�X���擾����B<br>
     * ���ӂꓮ��T�[�r�X���擾����B<br>
     * ���ӂꓮ��T�[�r�X�ɁA{@link OverflowAction#setOverflowController(OverflowController)}�Ŏ������g��ݒ肷��B<br>
     * �L���[�T�[�r�X���擾����B<br>
     * �f�[�������J�n����B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(validatorServiceName != null){
            validator = (OverflowValidator)ServiceManagerFactory
                .getServiceObject(validatorServiceName);
        }
        if(algorithmServiceName != null){
            algorithm = (OverflowAlgorithm)ServiceManagerFactory
                .getServiceObject(algorithmServiceName);
        }
        if(validator != null && algorithm == null){
            throw new IllegalArgumentException(
                "OverflowAlgorithm must specify when OverflowValidator is specified."
            );
        }
        if(actionServiceName != null){
            action = (OverflowAction)ServiceManagerFactory
                .getServiceObject(actionServiceName);
        }else{
            action = getDefaultOverflowActionService();
        }
        action.setOverflowController(this);
        
        if(queueServiceName != null){
            queue = (Queue)ServiceManagerFactory
                .getServiceObject(queueServiceName);
        }
        if(periodicOverflowIntervalTime > 0){
            periodicOverflowInterval = periodicOverflowIntervalTime;
        }
        if(queue != null || periodicOverflowInterval > 0){
            
            if(queue != null){
                // �L���[��t�J�n
                queue.accept();
            }
            
            // �f�[�����N��
            daemon.start();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     * �f�[�������~����B<br>
     * ���ӂꌟ�؃T�[�r�X�̎Q�Ƃ�j������B<br>
     * ���ӂ�A���S���Y���T�[�r�X�̎Q�Ƃ�j������B<br>
     * ���ӂꓮ��T�[�r�X�̎Q�Ƃ�j������B<br>
     * �L���[�T�[�r�X�̎Q�Ƃ�j������B<br>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        if(queue != null || periodicOverflowInterval > 0){
            
            // �f�[������~
            daemon.stop();
            
            if(queue != null){
                // �L���[��t��~
                queue.release();
            }
        }
        
        validator = null;
        algorithm = null;
        if(defaultAction != null && action == defaultAction){
            defaultAction.stop();
        }
        action = null;
        queue = null;
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * �f�t�H���g�̂��ӂꓮ��T�[�r�X��j������B<br>
     * �f�t�H���g�̃L���[�T�[�r�X��j������B<br>
     * �f�[������j������B<br>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        if(defaultAction != null){
            defaultAction.destroy();
            defaultAction = null;
        }
        daemon = null;
    }
    
    /**
     * �f�t�H���g�̂��ӂꓮ��T�[�r�X���擾����B<p>
     *
     * @return {@link RemoveOverflowActionService}
     * @exception Exception �f�t�H���g�̂��ӂꓮ��T�[�r�X�̐����E�N���Ɏ��s�����ꍇ
     */
    protected OverflowAction getDefaultOverflowActionService() throws Exception{
        if(defaultAction == null){
            final RemoveOverflowActionService act
                 = new RemoveOverflowActionService();
            act.create();
            act.start();
            defaultAction = act;
        }else if(defaultAction.getState() != STARTED){
            defaultAction.start();
        }
        return defaultAction;
    }
    
    /**
     * ���ӂꐧ����s���B<p>
     * ���ӂꐧ��́A�ʃX���b�h�ōs�����߁A�����ł́A�������s�킸�ɂ����ɏ�����߂��B<br>
     * �ʃX���b�h�ōs�����ӂꐧ��́A{@link #consume(Object, DaemonControl)}���Q�ƁB<br>
     *
     * @param ref �L���b�V���ɒǉ����ꂽ�L���b�V���Q��
     */
    public void control(CachedReference ref){
        if(getState() != STARTED){
            return;
        }
        if(queue == null){
            consume(ref, daemon);
        }else{
            queue.push(ref);
        }
    }
    
    /**
     * ���ӂꐧ����s�����߂ɕێ����Ă����������������B<p>
     * {@link OverflowValidator#reset()}�A{@link OverflowAlgorithm#reset()}�A{@link OverflowAction#reset()}���Ăяo���B<br>
     */
    public void reset(){
        if(validator != null){
            validator.reset();
        }
        if(algorithm != null){
            algorithm.reset();
        }
        if(action != null){
            action.reset();
        }
    }
    
    /**
     * �f�[�������J�n�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStart() {
        return true;
    }
    
    /**
     * �f�[��������~�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStop() {
        return true;
    }
    
    /**
     * �f�[���������f�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onSuspend() {
        return true;
    }
    
    /**
     * �f�[�������ĊJ�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onResume() {
        return true;
    }
    
    /**
     * �L���[����P���o���ĕԂ��B<p>
     * 
     * @param ctrl DaemonControl�I�u�W�F�N�g
     * @return {@link CachedReference}�I�u�W�F�N�g
     */
    public Object provide(DaemonControl ctrl){
        if(getState() != STARTED){
            return null;
        }
        CachedReference ref = null;
        if(queue == null){
            try{
                ctrl.sleep(periodicOverflowInterval, true);
            }catch(InterruptedException e){
            }
        }else{
            if(periodicOverflowInterval > 0){
                ref = (CachedReference)queue
                    .get(periodicOverflowInterval);
            }else{
                ref = (CachedReference)queue.get();
            }
        }
        return ref;
    }
    
    /**
     * ����dequeued�œn���ꂽ�I�u�W�F�N�g�������B<p>
     * ����dequeued�œn���ꂽ�I�u�W�F�N�g��{@link CachedReference}�ɃL���X�g����{@link OverflowValidator}�A{@link OverflowAlgorithm}�A{@link OverflowAction}���Ăяo���B<br>
     * ���ӂꐧ��́A{@link #isValidateByOverflow()}��false�̏ꍇ�́A�ȉ��̏����ōs����B<br>
     * <ol>
     *     <li>�P�D{@link OverflowValidator#add(CachedReference)}���Ăяo���B</li>
     *     <li>�Q�D{@link #isOverflowNewAdding()}��true�̏ꍇ�A{@link OverflowAlgorithm#add(CachedReference)}���Ăяo���B</li>
     *     <li>�R�D{@link #isOverflowByAdding()}��true�̏ꍇ�A{@link OverflowValidator#validate()}���Ăяo���A���̖߂�l�̂�1�ȏ�̏ꍇ�A�P�`�R�̏������s���B</li>
     *     <li>�R�|�P�D{@link OverflowValidator#validate()}���Ăяo���A���ӂꐔ�����肷��B</li>
     *     <li>�R�|�Q�D{@link OverflowAlgorithm#overflow(int)}���Ăяo���A���ӂ�Ώۂ̃L���b�V���Q�Ƃ����肷��B</li>
     *     <li>�R�|�R�D���ӂ�Ώۂ̃L���b�V���Q�Ƃ̐��̕������A{@link OverflowAction#action(OverflowValidator, OverflowAlgorithm, CachedReference)}���J��Ԃ��Ăяo���A���ӂꏈ�����s���B</li>
     *     <li>�S�D{@link #isOverflowNewAdding()}��false�̏ꍇ�A{@link OverflowAlgorithm#add(CachedReference)}���Ăяo���B</li>
     * </ol>
     * ���ӂꐧ��́A{@link #isValidateByOverflow()}��true�̏ꍇ�́A�ȉ��̏����ōs����B<br>
     * <ol>
     *     <li>�P�D{@link OverflowValidator#add(CachedReference)}���Ăяo���B</li>
     *     <li>�Q�D{@link #isOverflowNewAdding()}��true�̏ꍇ�A{@link OverflowAlgorithm#add(CachedReference)}���Ăяo���B</li>
     *     <li>�R�D{@link #isOverflowByAdding()}��true�̏ꍇ�A{@link OverflowValidator#validate()}���Ăяo���A���̖߂�l�̂�1�ȏ�̏ꍇ�A�ȉ��̂P�`�R�̏������J��Ԃ��B</li>
     *     <li>�R�|�P�D{@link OverflowAlgorithm#overflow()}���Ăяo���A���ӂ�Ώۂ̃L���b�V���Q�Ƃ����肷��B���ӂ�Ώۂ̃L���b�V���Q�Ƃ�null�̏ꍇ�́Abreak</li>
     *     <li>�R�|�Q�D���ӂ�Ώۂ̃L���b�V���Q�Ƃ�{@link OverflowAction#action(OverflowValidator, OverflowAlgorithm, CachedReference)}�ɓn���āA���ӂꏈ�����s���B</li>
     *     <li>�R�|�R�D{@link OverflowValidator#validate()}���Ăяo���A���ӂꐔ���ĕ]�����A0�ȉ��ƂȂ�ꍇ�́Abreak�B</li>
     *     <li>�S�D{@link #isOverflowNewAdding()}��false�̏ꍇ�A{@link OverflowAlgorithm#add(CachedReference)}���Ăяo���B</li>
     * </ol>
      *
     * @param dequeued �L���[������o���ꂽ�I�u�W�F�N�g
     * @param ctrl DaemonControl�I�u�W�F�N�g
     */
    public void consume(Object dequeued, DaemonControl ctrl){
        if(validator == null || getState() != STARTED){
            return;
        }
        CachedReference ref = (CachedReference)dequeued;
        if(ref != null && !ref.isRemoved() && validator != null){
            validator.add(ref);
        }
        if(isOverflowNewAdding && ref != null && !ref.isRemoved() && algorithm != null){
            algorithm.add(ref);
        }
        int overflowSize = 0;
        if(ref == null || isOverflowByAdding){
            overflowSize = validator.validate();
        }
        if(overflowSize > 0){
            if(!isValidateByOverflow){
                synchronized(lock){
                    overflowSize = validator.validate();
                    if(overflowSize > 0){
                        CachedReference[] overflowRefs = null;
                        if(algorithm != null){
                            overflowRefs = algorithm.overflow(overflowSize);
                        }
                        if(overflowRefs != null){
                            for(int i = 0; i < overflowRefs.length; i++){
                                CachedReference overflowRef = overflowRefs[i];
                                if(overflowRef == null){
                                    continue;
                                }else if(!overflowRef.isRemoved()){
                                    if(overflowRef != null && action != null && !overflowRef.isRemoved()){
                                        action.action(validator, algorithm, overflowRef);
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                CachedReference prevOverflowRef = null;
                while(overflowSize > 0){
                    CachedReference overflowRef = null;
                    if(algorithm != null){
                        overflowRef = algorithm.overflow();
                    }
                    if(prevOverflowRef != null && prevOverflowRef == overflowRef){
                        // ���ӂꓮ�삪�s���Ȃ��ꍇ�̖������[�v���������
                        break;
                    }
                    if(overflowRef == null){
                        break;
                    }else if(!overflowRef.isRemoved()){
                        if(overflowRef != null && action != null && !overflowRef.isRemoved()){
                            action.action(validator, algorithm, overflowRef);
                        }
                    }
                    if(validator != null){
                        overflowSize = validator.validate();
                    }else{
                        overflowSize = 0;
                    }
                    prevOverflowRef = overflowRef;
                }
            }
        }
        if(!isOverflowNewAdding && ref != null && !ref.isRemoved() && algorithm != null){
            algorithm.add(ref);
        }
    }
    
    /**
     * �L���[�̒��g��f���o���B<p>
     */
    public void garbage(){
        if(queue != null){
            while(queue.size() > 0){
                consume(queue.get(0), daemon);
            }
        }
    }
}
