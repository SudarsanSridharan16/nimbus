package jp.ossc.nimbus.service.writer;

import jp.ossc.nimbus.service.crypt.Crypt;

/**
 * �C�ӂ̕�������Í�������{@link WritableElement}�����N���X�B<p>
 */
public class CryptElement extends SimpleElement {
    
    private static final long serialVersionUID = -1736395385754458771L;
    
    /** �Í����T�[�r�X */
    private Crypt crypt;
    
    /**
     * �Í����T�[�r�X��ݒ肷��B<p>
     * 
     * @param crypt �Í����T�[�r�X
     */
    public void setCrypt(Crypt crypt){
        this.crypt = crypt;
    }
    
    /**
     * �Í����T�[�r�X���擾����B<p>
     * 
     * @return �Í����T�[�r�X
     */
    public Crypt getCrypt(){
        return crypt;
    }
    
    /**
     * ���̗v�f(������)���Í������Ď擾����B<p>
     * 
     * @return ���̗v�f(������)���Í�����������
     */
    public String toString(){
        if(mValue == null){
            return null;
        }
        
        String ret = null;
        if(crypt != null
            && mValue instanceof String){
            ret = crypt.doEncode((String) mValue);
        }
        
        return convertString(ret);
    }

    /**
     * ���̗v�f(������)���Í������Ď擾����B<p>
     * {@link #toString()}�Ɠ����l��Ԃ��B<br>
     * 
     * @return ���̗v�f(������)���Í�����������
     */
    public Object toObject(){
        return toString();
    }

}
