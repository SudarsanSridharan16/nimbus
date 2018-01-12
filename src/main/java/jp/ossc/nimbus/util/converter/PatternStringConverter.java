package jp.ossc.nimbus.util.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.lang.IllegalArgumentException;

/**
 * ���K�\���R���o�[�^�B<p>
 * 
 * @author Y.Yamashina
 */
public class PatternStringConverter
 implements StringConverter, java.io.Serializable{
    
    private static final long serialVersionUID = 9056240502325078689L;
    
    /**
     * �ϊ��p�^�[���z��B<p>
     */
    protected Object[][] convertObjects;
    
    /**
     * �}�b�`���O�t���O�B<p>
     */
    protected int matchingFlag = -1;
    
    /**
     * ��̐��K�\���R���o�[�^�𐶐�����B<p>
     */
    public PatternStringConverter(){
    }
    
    /**
     * �w�肳�ꂽ�}�b�`�t���O�̐��K�\���R���o�[�^�𐶐�����B<p>
     *
     * @param flags �}�b�`�t���O
     */
    public PatternStringConverter(int flags){
        setMatchingFlag(flags);
    }
    
    /**
     * �w�肳�ꂽ�}�b�`�t���O�ƕϊ��p�^�[���̐��K�\���R���o�[�^�𐶐�����B<p>
     *
     * @param fromStrs �ϊ��Ώې��K�\���p�^�[��������z��
     * @param toStrs �ϊ��㕶����z��
     */
    public PatternStringConverter(
        int flags,
        String[] fromStrs,
        String[] toStrs
    ){
        setMatchingFlag(flags);
        setConvertStrings(fromStrs, toStrs);
    }
    
    /**
     * ���K�\���̃}�b�`���O���s�����̃}�b�`�t���O��ݒ肷��B<p>
     * �}�b�`�t���O�Ƃ́APattern.CASE_INSENSITIVE�APattern.MULTILINE�APattern.DOTALL�APattern.UNICODE_CASE�APattern.CANON_EQ �Ȃǂ��܂ރr�b�g�}�X�N�ł���B
     *
     * @param flags �}�b�`�t���O
     */
    public void setMatchingFlag(int flags){
        // �}�b�`���O�t���O���ύX���ꂽ�ꍇ�Apattern���č쐬
        if(matchingFlag != flags && convertObjects != null){
            for(int i = 0; i < convertObjects.length; i++){
                Pattern pattern = (Pattern)convertObjects[i][0];
                
                Pattern newPattern = null;
                if(flags != -1){
                    newPattern = Pattern.compile(pattern.pattern(), flags);
                } else {
                    newPattern = Pattern.compile(pattern.pattern());
                }
                convertObjects[i][0] = newPattern;
            }
        }
        matchingFlag = flags;
    }
    
    /**
     * ���K�\���̃}�b�`���O���s�����̃}�b�`�t���O���擾����B<p>
     *
     * @return �}�b�`�t���O
     */
    public int getMatchingFlag(){
        return matchingFlag;
    }
    
    /**
     * �ϊ��p�^�[����ݒ肷��B<p>
     *
     * @param fromStrs �ϊ��Ώې��K�\���p�^�[��������z��
     * @param toStrs �ϊ��㕶����z��
     */
    public void setConvertStrings(String[] fromStrs, String[] toStrs){
        if(toStrs == null && fromStrs == null){
            convertObjects = null;
        }else if((toStrs == null || fromStrs == null)
            || toStrs.length != fromStrs.length){
            throw new IllegalArgumentException("Invalid ConvertStrings.");
        }else{
            final Object[][] convObjs = new Object[toStrs.length][];
            try{
                for(int i = 0; i < toStrs.length; i++){
                    if(fromStrs[i] == null || toStrs[i] == null){
                        throw new IllegalArgumentException(
                            "Invalid ConvertStrings."
                        );
                    }
                    Pattern pattern = null;
                    if(matchingFlag!=-1) {
                    	pattern = Pattern.compile(fromStrs[i], matchingFlag);
                    } else {
                    	pattern = Pattern.compile(fromStrs[i]);
                    }
                    convObjs[i] = new Object[]{pattern, toStrs[i]};
                }
            }catch(PatternSyntaxException pe){
                //��`���ꂽ�}�b�`�t���O�ɑΉ����Ȃ��r�b�g�l�� flags �ɐݒ肳�ꂽ�ꍇ
                throw new IllegalArgumentException("Invalid ConvertStrings.");
            }catch(IllegalArgumentException ie){
                //�\���̍\���������ł���ꍇ
                throw new IllegalArgumentException("Invalid ConvertStrings.");
            }
            
            convertObjects = convObjs;
        }
    }
    
    // Converter��JavaDoc
    public Object convert(Object obj) throws ConvertException{
        if(obj == null){
            return null;
        }else{
            return convert(
                (String)(obj instanceof String ? obj : String.valueOf(obj))
            );
        }
    }
    
    /**
     * �������ϊ�����B<p>
     * �ϊ��p�^�[���z����g���ĕϊ�����B<br>
     *
     * @param str �ϊ��Ώۂ̕����� 
     * @return �ϊ���̕�����
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public String convert(String str) throws ConvertException{
        String result = str;
        final Object[][] convObjects = convertObjects;
        
        if(convObjects != null){
            for(int i = 0; i < convObjects.length; i++){
                Pattern pattern = (Pattern)convObjects[i][0];
                
                Matcher matcher = pattern.matcher(result);
                result = matcher.replaceAll((String)convObjects[i][1]);
            }
        }
        return result;
    }
}
