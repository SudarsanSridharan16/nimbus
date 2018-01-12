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

/**
 * �T�[�o���B<p>
 *
 * @author M.Takata
 */
public interface ServerInfo extends Context{
    
    /**
     * JRE�̃o�[�W���������擾����L�[�B<p>
     */
    public static final String JAVA_VERSION_KEY = "JAVA_VERSION";
    
    /**
     * JRE�̃x���_�����擾����L�[�B<p>
     */
    public static final String JAVA_VENDOR_KEY = "JAVA_VENDOR";
    
    /**
     * JVM�̖��O���擾����L�[�B<p>
     */
    public static final String JAVA_VM_NAME_KEY = "JAVA_VM_NAME";
    
    /**
     * JVM�̃o�[�W���������擾����L�[�B<p>
     */
    public static final String JAVA_VM_VERSION_KEY = "JAVA_VM_VERSION";
    
    /**
     * JVM�̃x���_�����擾����L�[�B<p>
     */
    public static final String JAVA_VM_VENDOR_KEY = "JAVA_VM_VENDOR";
    
    /**
     * OS�̖��O���擾����L�[�B<p>
     */
    public static final String OS_NAME_KEY = "OS_NAME";
    
    /**
     * OS�̃o�[�W���������擾����L�[�B<p>
     */
    public static final String OS_VERSION_KEY = "OS_VERSION";
    
    /**
     * OS�̃A�[�L�e�N�`�������擾����L�[�B<p>
     */
    public static final String OS_ARCH_KEY = "OS_ARCH";
    
    /**
     * �q�[�v�������̌��݂̑��e�ʂ��擾����L�[�B<p>
     */
    public static final String TOTAL_MEMORY_KEY = "TOTAL_MEMORY";
    
    /**
     * �q�[�v�������̌��݂̎g�p�ʂ��擾����L�[�B<p>
     */
    public static final String USED_MEMORY_KEY = "USED_MEMORY";
    
    /**
     * �q�[�v�������̌��݂̋󂫗e�ʂ��擾����L�[�B<p>
     */
    public static final String FREE_MEMORY_KEY = "FREE_MEMORY";
    
    /**
     * �q�[�v�������̍ő�e�ʂ��擾����L�[�B<p>
     */
    public static final String MAX_MEMORY_KEY = "MAX_MEMORY";
    
    /**
     * �g�p�\��CPU�̐����擾����L�[�B<p>
     */
    public static final String AVAILABLE_PROCESSORS_KEY
         = "AVAILABLE_PROCESSORS";
    
    /**
     * �z�X�g�����擾����L�[�B<p>
     */
    public static final String HOST_NAME_KEY = "HOST_NAME";
    
    /**
     * �z�X�g�̃A�h���X���擾����L�[�B<p>
     */
    public static final String HOST_ADDRESS_KEY = "HOST_ADDRESS";
    
    /**
     * ���݃A�N�e�B�u�ȃX���b�h�����擾����L�[�B<p>
     */
    public static final String ACTIVE_THREAD_COUNT_KEY = "ACTIVE_THREAD_COUNT";
    
    /**
     * ���݃A�N�e�B�u�ȃX���b�h�O���[�v�����擾����L�[�B<p>
     */
    public static final String ACTIVE_THREAD_GROUP_COUNT_KEY
         = "ACTIVE_THREAD_GROUP_COUNT";
    
    /**
     * JRE�̃o�[�W���������擾����B<p>
     *
     * @return JRE�̃o�[�W�������
     */
    public String getJavaVersion();
    
    /**
     * JRE�̃x���_�����擾����B<p>
     *
     * @return JRE�̃x���_���
     */
    public String getJavaVendor();
    
    /**
     * JVM�̖��O���擾����B<p>
     *
     * @return JVM�̖��O
     */
    public String getJavaVMName();
    
    /**
     * JVM�̃o�[�W���������擾����B<p>
     *
     * @return JVM�̃o�[�W�������
     */
    public String getJavaVMVersion();
    
    /**
     * JVM�̃x���_�����擾����B<p>
     *
     * @return JVM�̃x���_���
     */
    public String getJavaVMVendor();
    
    /**
     * OS�̖��O���擾����B<p>
     *
     * @return OS�̖��O
     */
    public String getOSName();
    
    /**
     * OS�̃o�[�W���������擾����B<p>
     *
     * @return OS�̃o�[�W�������
     */
    public String getOSVersion();
    
    /**
     * OS�̃A�[�L�e�N�`�������擾����B<p>
     *
     * @return OS�̃A�[�L�e�N�`�����
     */
    public String getOSArch();
    
    /**
     * ���݂̃q�[�v�������̑��e��[byte]���擾����B<p>
     *
     * @return ���݂̃q�[�v�������̑��e��[byte]
     */
    public long getTotalMemory();
    
    /**
     * ���݂̃q�[�v�������̎g�p��[byte]���擾����B<p>
     *
     * @return ���݂̃q�[�v�������̎g�p��[byte]
     */
    public long getUsedMemory();
    
    /**
     * ���݂̃q�[�v�������̋󂫗e��[byte]���擾����B<p>
     *
     * @return ���݂̃q�[�v�������̋󂫗e��[byte]
     */
    public long getFreeMemory();
    
    /**
     * �q�[�v�������̍ő�e��[byte]���擾����B<p>
     *
     * @return �q�[�v�������̍ő�e��[byte]
     */
    public long getMaxMemory();
    
    /**
     * �g�p�\��CPU�̐����擾����B<p>
     *
     * @return �g�p�\��CPU�̐�
     */
    public int getAvailableProcessors();
    
    /**
     * �z�X�g�����擾����B<p>
     *
     * @return �z�X�g��
     */
    public String getHostName();
    
    /**
     * �z�X�g�̃A�h���X���擾����B<p>
     *
     * @return �z�X�g�̃A�h���X
     */
    public String getHostAddress();
    
    /**
     * ���݃A�N�e�B�u�ȃX���b�h�����擾����B<p>
     *
     * @return ���݃A�N�e�B�u�ȃX���b�h��
     */
    public int getActiveThreadCount();
    
    /**
     * ���݃A�N�e�B�u�ȃX���b�h�O���[�v�����擾����B<p>
     *
     * @return ���݃A�N�e�B�u�ȃX���b�h�O���[�v��
     */
    public int getActiveThreadGroupCount();
}