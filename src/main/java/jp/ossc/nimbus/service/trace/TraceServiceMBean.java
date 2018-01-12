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
// �p�b�P�[�W
package jp.ossc.nimbus.service.trace;
//�C���|�[�g
import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link TraceService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author K.Nagai
 */
public interface TraceServiceMBean extends ServiceBaseMBean {

	/**�g���[�X�p�̃��b�Z�[�W���R�[�h�L�[*/
	//�g���[�X�擾�v����t��
	public static final String TRACE_ENTRY_KEY     = "TRC__00001";
	//�g���[�X�擾�v���I����
	public static final String TRACE_EXIT_KEY      = "TRC__00002";
	//�l�X�g���x���ُ픭����
	public static final String TRACE_NESTLEVEL_ERR = "TRC__00003";
	/**�g���[�X�擾���x���F�o�͂Ȃ�*/
	public static final int DISABLE_LEVEL   = 21;
	/**�g���[�X�擾���x���FPUBLIC*/
	public static final int PUBLIC_LEVEL    = 20;
	/**�g���[�X�擾���x���FPROTECTED*/
	public static final int PROTECTED_LEVEL = 10;
	/**�g���[�X�擾���x���FPRIVATE*/
	public static final int PRIVATE_LEVEL   = 0;
	
	/** �����I�Ɏg�p���郍�O�T�[�r�X���擾*/
	public ServiceName getLogServiceName();
	/** �����I�Ɏg�p���郍�O�T�[�r�X���ݒ�*/
	public void setLogServiceName(ServiceName name);

	/** �����I�Ɏg�p����G�f�B�^�t�@�C���_�T�[�r�X���擾*/
	public ServiceName getEditorFinderServiceName();
	/** �����I�Ɏg�p����G�f�B�^�t�@�C���_�T�[�r�X���ݒ�*/
	public void setEditorFinderServiceName(ServiceName name);
	/** �g���[�X���x���ݒ�*/
	public void setTraceLevel(int level);
	/** �l�X�g���x���ݒ�*/
	public void setNestedLevel(int level);
	/** �Z�p���^�ݒ�*/
	public void setSeparator(String sep);
}
