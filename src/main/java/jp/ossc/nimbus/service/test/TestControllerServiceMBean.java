package jp.ossc.nimbus.service.test;

import java.io.File;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link TestControllerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Ishida
 * @see TestControllerService
 */
public interface TestControllerServiceMBean extends ServiceBaseMBean {
    
    public static final String USERID_PROPERTY_KEY_NAME = "test.executer";
    public static final String DEFAULT_SCENARIO_GROUP_RESOURCE_FILE_NAME = "scenarioGroup.xml";
    public static final String DEFAULT_SCENARIO_RESOURCE_FILE_NAME = "scenario.xml";
    public static final String DEFAULT_TESTCASE_RESOURCE_FILE_NAME = "testcase.xml";
    
    /**
     * �e�X�g���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.TestResourceManager
     * TestResourceManager}�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     * 
     * @return TestResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTestResourceManagerServiceName();
    
    /**
     * �e�X�g���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.TestResourceManager
     * TestResourceManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     * 
     * @param serviceName TestResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public void setTestResourceManagerServiceName(ServiceName serviceName);
    
    /**
     * �e�X�g���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.TestResourceManager
     * TestResourceManager}�T�[�r�X���擾����B
     * <p>
     * 
     * @return TestResourceManager�T�[�r�X
     */
    public TestResourceManager getTestResourceManager();
    
    /**
     * �e�X�g���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.TestResourceManager
     * TestResourceManager}�T�[�r�X��ݒ肷��B
     * <p>
     * 
     * @param manager TestResourceManager�T�[�r�X
     */
    public void setTestResourceManager(TestResourceManager manager);
    
    /**
     * �X�^�u���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.StubResourceManager
     * StubResourceManager}�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     * 
     * @return StubResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getStubResourceManagerServiceName();
    
    /**
     * �X�^�u���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.StubResourceManager
     * StubResourceManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     * 
     * @param serviceName StubResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public void setStubResourceManagerServiceName(ServiceName serviceName);
    
    /**
     * �X�^�u���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.StubResourceManager
     * StubResourceManager}�T�[�r�X���擾����B
     * <p>
     * 
     * @return StubResourceManager�T�[�r�X
     */
    public StubResourceManager getStubResourceManager();
    
    /**
     * �X�^�u���\�[�X���Ǘ�����{@link jp.ossc.nimbus.service.test.StubResourceManager
     * StubResourceManager}�T�[�r�X�̐ݒ肷��B
     * <p>
     * 
     * @param manager StubResourceManager�T�[�r�X
     */
    public void setStubResourceManager(StubResourceManager manager);
    
    /**
     * �X�^�u{@link jp.ossc.nimbus.service.test.TestStub TestStub}
     * �T�[�r�X�̃T�[�r�X���̔z����擾����B
     * <p>
     * 
     * @return TestStub�T�[�r�X�̃T�[�r�X���̔z��
     */
    public ServiceName[] getTestStubServiceNames();
    
    /**
     * �X�^�u{@link jp.ossc.nimbus.service.test.TestStub TestStub}
     * �T�[�r�X�̃T�[�r�X���̔z���ݒ肷��B
     * <p>
     * 
     * @param serviceNames TestStub�T�[�r�X�̃T�[�r�X���̔z��
     */
    public void setTestStubServiceNames(ServiceName[] serviceNames);
    
    /**
     * �X�^�u{@link jp.ossc.nimbus.service.test.TestStub TestStub}�T�[�r�X���擾����B
     * <p>
     * 
     * @return TestStub�T�[�r�X�̔z��
     */
    public TestStub[] getTestStubs();
    
    /**
     * �X�^�u{@link jp.ossc.nimbus.service.test.TestStub TestStub}�T�[�r�X��ݒ肷��B
     * <p>
     * 
     * @param stubs TestStub�T�[�r�X�̔z��
     */
    public void setTestStubs(TestStub[] stubs);
    
    /**
     * �C�x���g���X�i{@link jp.ossc.nimbus.service.test.TestEventListener
     * TestEventListener}�T�[�r�X�̃T�[�r�X���̔z����擾����B
     * <p>
     * 
     * @return TestEventListener�T�[�r�X�̃T�[�r�X���̔z��
     */
    public ServiceName[] getTestEventListenerServiceNames();
    
    /**
     * �C�x���g���X�i{@link jp.ossc.nimbus.service.test.TestEventListener
     * TestEventListener}�T�[�r�X�̃T�[�r�X���̔z���ݒ肷��B
     * <p>
     * 
     * @param serviceNames TestEventListener�T�[�r�X�̃T�[�r�X���̔z��
     */
    public void setTestEventListenerServiceNames(ServiceName[] serviceNames);
    
    /**
     * �C�x���g���X�i{@link jp.ossc.nimbus.service.test.TestEventListener
     * TestEventListener}�T�[�r�X�̔z����擾����B
     * <p>
     * 
     * @return TestEventListener�T�[�r�X�̔z��
     */
    public TestEventListener[] getTestEventListeners();
    
    /**
     * �C�x���g���X�i{@link jp.ossc.nimbus.service.test.TestEventListener
     * TestEventListener}�T�[�r�X�̔z���ݒ肷��B
     * <p>
     * 
     * @param listeners TestEventListener�T�[�r�X�̔z��
     */
    public void setTestEventListeners(TestEventListener[] listeners);
    
    /**
     * �e�X�g�R���g���[�����g�p���郊�\�[�X�Q��ۑ�����x�[�X�f�B���N�g�����擾����B
     * <p>
     * 
     * @return ���\�[�X�Q��ۑ�����x�[�X�f�B���N�g��
     */
    public File getTestResourceFileBaseDirectory();
    
    /**
     * �e�X�g�R���g���[�����g�p���郊�\�[�X�Q��ۑ�����x�[�X�f�B���N�g����ݒ肷��B
     * <p>
     * 
     * @param dir ���\�[�X�Q��ۑ�����x�[�X�f�B���N�g��
     */
    public void setTestResourceFileBaseDirectory(File dir);
    
    /**
     * �e�X�g�R���g���[�����g�p����ꎞ�t�@�C����ۑ�����x�[�X�f�B���N�g�����擾����B
     * <p>
     * 
     * @return �ꎞ�t�@�C����ۑ�����x�[�X�f�B���N�g��
     */
    public File getTestResourceFileTempDirectory();
    
    /**
     * �e�X�g�R���g���[�����g�p����ꎞ�t�@�C����ۑ�����x�[�X�f�B���N�g����ݒ肷��B
     * <p>
     * �ȗ����̓V�X�e����Temp�f�B���N�g�����g�p����B<br>
     * 
     * @param dir �ꎞ�t�@�C����ۑ�����x�[�X�f�B���N�g��
     */
    public void setTestResourceFileTempDirectory(File dir);
    
    public String getScenarioGroupResourceFileName();
    
    public void setScenarioGroupResourceFileName(String fileName);
    
    public String getScenarioResourceFileName();
    
    public void setScenarioResourceFileName(String fileName);
    
    public void setTestPhase(String phase);
    
    public String getTestPhase();
    
    public void setTestCaseResourceFileName(String fileName);
    
    public String getTestCaseResourceFileName();
    
}
