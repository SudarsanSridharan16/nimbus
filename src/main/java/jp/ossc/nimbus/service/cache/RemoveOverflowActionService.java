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

import jp.ossc.nimbus.core.ServiceBase;

/**
 * �폜���ӂꓮ��T�[�r�X�B<p>
 * �L���b�V�����炠�ӂꂽ�L���b�V���I�u�W�F�N�g���폜���邠�ӂꓮ��ł���B<br>
 * �ȉ��ɁA�폜���ӂꓮ��T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="RemoveOverflowAction"
 *                  code="jp.ossc.nimbus.service.cache.RemoveOverflowActionService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class RemoveOverflowActionService extends ServiceBase
 implements OverflowAction, java.io.Serializable,
            RemoveOverflowActionServiceMBean{
    
    private static final long serialVersionUID = -2957032692475860024L;
    
    // OverflowAction��JavaDoc
    public void setOverflowController(OverflowController controller){
    }
    
    /**
     * ���ӂꂽ�L���b�V���I�u�W�F�N�g���L���b�V������폜����B<p>
     *
     * @param validator ���ӂꌟ�؂��s����OverflowValidator
     * @param algorithm ���ӂ�L���b�V���Q�Ƃ����肵��OverflowAlgorithm
     * @param ref ���ӂꂽ�L���b�V���Q��
     */
    public void action(
        OverflowValidator validator,
        OverflowAlgorithm algorithm,
        CachedReference ref
    ){
        if(ref != null){
            ref.remove(this);
            if(validator != null){
                validator.remove(ref);
            }
            if(algorithm != null){
                algorithm.remove(ref);
            }
        }
    }
    
    // OverflowAction��JavaDoc
    public void reset(){
    }
}
