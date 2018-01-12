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
package jp.ossc.nimbus.service.test;

import java.util.Date;

/**
 * �e�X�g�V�i���I�B<p>
 * 
 * @author M.Ishida
 */
public interface TestScenario {
    
    /**
     * �e�X�g�V�i���I����������V�i���I�O���[�v��ID���擾����B<p>
     *
     * @return �V�i���I�O���[�v��ID
     */
    public String getScenarioGroupId();
    
    /**
     * �e�X�g�V�i���I��ID���擾����B<p>
     *
     * @return �e�X�g�V�i���I��ID
     */
    public String getScenarioId();
    
    /**
     * �e�X�g�V�i���I�̃��\�[�X�����擾����B<p>
     *
     * @return �e�X�g�V�i���I�̃��\�[�X���
     * @exception Exception �e�X�g�V�i���I�̃��\�[�X���̎擾�Ɏ��s�����ꍇ
     */
    public TestScenarioResource getTestScenarioResource() throws Exception;
    
    /**
     * �e�X�g�V�i���I�̎��s�X�e�[�^�X���擾����B<p>
     *
     * @return �e�X�g�V�i���I�̎��s�X�e�[�^�X
     */
    public Status getStatus();
    
    /**
     * �e�X�g�V�i���I�̃��\�[�X���B<p>
     * 
     * @author M.Ishida
     * @see <a href="scenario_1_0.dtd">�e�X�g�V�i���I��`�t�@�C��DTD</a>
     */
    public interface TestScenarioResource extends ScheduledTestResource {
        
        /**
         * ���s�\��҂��擾����B<p>
         *
         * @return ���s�\���
         */
        public String getScheduledExcutor();
        
        /**
         * ���s�\������擾����B<p>
         *
         * @return ���s�\���
         */
        public Date getScheduledExcuteDate();
        
        /**
         * ���O�A�N�V������ID�z����擾����B<p>
         *
         * @return ���O�A�N�V������ID�z��
         */
        public String[] getBeforeActionIds();
        
        /**
         * ����A�N�V������ID�z����擾����B<p>
         *
         * @return ����A�N�V������ID�z��
         */
        public String[] getAfterActionIds();
        
        /**
         * �ŏI�A�N�V������ID�z����擾����B<p>
         *
         * @return �ŏI�A�N�V������ID�z��
         */
        public String[] getFinallyActionIds();
        
    }
    
    /**
     * �e�X�g�V�i���I�̎��s�X�e�[�^�X�B<p>
     * 
     * @author M.Ishida
     */
    public interface Status extends StatusActionMnager {
        
        /**
         * ��ԁF�����B<p>
         */
        public static final int INITIAL = 0;
        /**
         * ��ԁF�J�n�B<p>
         */
        public static final int STARTED = 1;
        /**
         * ��ԁF�I���B<p>
         */
        public static final int END = 2;
        /**
         * ��ԁF����B<p>
         */
        public static final int CANCELED = 3;
        /**
         * ��ԁF�ُ�B<p>
         */
        public static final int ERROR = 4;
        
        /**
         * ��Ԃ��擾����B<p>
         *
         * @return ���
         * @see #INITIAL
         * @see #STARTED
         * @see #END
         * @see #CANCELED
         * @see #ERROR
         */
        public int getState();
        
        /**
         * ��ԕ�������擾����B<p>
         *
         * @return ��ԕ�����
         */
        public String getStateString();
        
        /**
         * �I���������擾����B<p>
         *
         * @return �I������
         */
        public Date getEndTime();
    }
}
