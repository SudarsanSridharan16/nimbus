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
package jp.ossc.nimbus.service.ioccall;

import jp.ossc.nimbus.ioc.*;

/**
 * NimbusIOC��Facade���R�[������C���^�[�t�F�C�X�B<p>
 *
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface FacadeCaller {
    
    /**
     * �����I��NimbusIOC�̃R�}���h�wEJB�R�[�����s���B<p>
     * 
     * @param value ���̓R�}���h
     * @return �R�}���h���s����
     */
    public Command syncCommandCall(Command value);
    
    /**
     * �����I��NimbusIOC�̃��j�b�g�I�u���[�N�wEJB�R�[�����s���B<p>
     * �����̃R�}���h���P�̃g�����U�N�V�������ŏ����������ꍇ�Ɏg�p����B<br>
     *
     * @param value ���̓R�}���h�W��
     * @return �R�}���h���s���ʏW��
     */
    public UnitOfWork syncUnitOfWorkCall(UnitOfWork value);
    
    /**
     * �����I��NimbusIOC��EJB�t�@�T�[�h�R�[�����s���B<p>
     * �����̃g�����U�N�V���������������������ꍇ�Ɏg�p����B<br>
     * 
     * @param value ���̓g�����U�N�V�����W��
     * @return �g�����U�N�V�������s���ʏW��
     */
    public FacadeValue syncFacadeCall(FacadeValue value);
    
    /**
     * �����I��NimbusIOC��EJB�t�@�T�[�h�R�[�����s���B<p>
     * �����̃g�����U�N�V�����W���𕽍s�����������ꍇ�Ɏg�p����B<br>
     * 
     * @param values ���̓g�����U�N�V�����W���z��
     * @return �g�����U�N�V�������s���ʏW���z��
     */
    public FacadeValue[] syncParallelFacadeCall(FacadeValue[] values);
    
    /**
     * �����I��NimbusIOC��EJB�t�@�T�[�h�R�[�����s���B<p>
     * �����̃g�����U�N�V�����W���𕽍s�����������ꍇ�Ɏg�p����B<br>
     * �^�C���A�E�g���������́A�߂�̔z��v�f��null���i�[����B<br>
     * 
     * @param values ���̓g�����U�N�V�����W���z��
     * @param timeout ���̓g�����U�N�V���������^�C���A�E�g[ms]
     * @return �g�����U�N�V�������s���ʏW���z��
     */
    public FacadeValue[] syncParallelFacadeCall(
        FacadeValue[] values,
        long timeout
    );
    
    /**
     * �񓯊���NimbusIOC��EJB�t�@�T�[�h�R�[�����s���B<p>
     * �����̃g�����U�N�V������񓯊������������ꍇ�Ɏg�p����B<br>
     * 
     * @param value ���̓g�����U�N�V�����W��
     */
    public void unsyncFacadeCall(FacadeValue value);
    
    /**
     * �񓯊���NimbusIOC��EJB�t�@�T�[�h�R�[�����s���B<p>
     * �����̃g�����U�N�V�����W����񓯊������������ꍇ�Ɏg�p����B<br>
     *
     * @param values ���̓g�����U�N�V�����W���z��
     */
    public void unsyncFacadeCall(FacadeValue[] values);
}
