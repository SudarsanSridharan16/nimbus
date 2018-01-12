package jp.ossc.nimbus.service.test.action;

/**
 * {@link CommandExecuteActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author T.Takakura
 * @see CommandExecuteActionService
 */
public interface CommandExecuteActionServiceMBean {
    
    /**
     * �R�}���h���s���ɓK�p������ϐ���ݒ肷��B<p>
     *
     * @param environments ���ϐ��B�ϐ���=�l�̔z��Ŏw�肷��B
     */
    public void setEnvironments(String[] environments);
    
    /**
     * �R�}���h���s���ɓK�p������ϐ����擾����B<p>
     *
     * @return ���ϐ��B�ϐ���=�l�̔z��Ŏw�肷��B
     */
    public String[] getEnvironments();
    
    /**
     * ���O�t�@�C���̏I���҂�������ꍇ�́A���O�t�@�C���`�F�b�N�Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param interval �`�F�b�N�Ԋu
     */
    public void setCheckInterval(long interval);
    
    /**
     * ���O�t�@�C���̏I���҂�������ꍇ�́A���O�t�@�C���`�F�b�N�Ԋu[ms]���擾����B<p>
     *
     * @return �`�F�b�N�Ԋu
     */
    public long getCheckInterval();
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B<p>
     * 
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �z��R�X�g
     */
    public double getExpectedCost();

}
