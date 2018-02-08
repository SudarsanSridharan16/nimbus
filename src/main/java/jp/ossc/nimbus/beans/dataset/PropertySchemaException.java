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
package jp.ossc.nimbus.beans.dataset;

/**
 * �v���p�e�B�X�L�[�}��O�B<p>
 * 
 * @author M.Takata
 */
public class PropertySchemaException extends DataSetException{
    
    private static final long serialVersionUID = -8950401972831897014L;
    
    /**
     * �v���p�e�B�X�L�[�}�B<p>
     */
    protected PropertySchema propertySchema;
    
    /**
     * ��̗�O�𐶐�����B<p>
     *
     * @param propertySchema �v���p�e�B�X�L�[�}
     */
    public PropertySchemaException(PropertySchema propertySchema){
        this.propertySchema = propertySchema;
    }
    
    /**
     * ���b�Z�[�W����������O�𐶐�����B<p>
     *
     * @param propertySchema �v���p�e�B�X�L�[�}
     * @param message ���b�Z�[�W
     */
    public PropertySchemaException(
        PropertySchema propertySchema,
        String message
    ){
        super(message);
        this.propertySchema = propertySchema;
    }
    
    /**
     * ���b�Z�[�W�ƌ����ƂȂ�����O����������O�𐶐�����B<p>
     *
     * @param propertySchema �v���p�e�B�X�L�[�}
     * @param message ���b�Z�[�W
     * @param cause �����ƂȂ�����O
     */
    public PropertySchemaException(PropertySchema propertySchema, String message, Throwable cause){
        super(message, cause);
        this.propertySchema = propertySchema;
    }
    
    /**
     * �����ƂȂ�����O����������O�𐶐�����B<p>
     *
     * @param propertySchema �v���p�e�B�X�L�[�}
     * @param cause �����ƂȂ�����O
     */
    public PropertySchemaException(PropertySchema propertySchema, Throwable cause){
        super(cause);
        this.propertySchema = propertySchema;
    }
    
    /**
     * ���b�Z�[�W���擾����B<p>
     *
     * @return ���b�Z�[�W
     */
    public String getMessage(){
        return (propertySchema == null ? "" : propertySchema.toString() + ':') + message;
    }
    
    /**
     * �v���p�e�B�X�L�[�}���擾����B<p>
     *
     * @return �v���p�e�B�X�L�[�}
     */
    public PropertySchema getPropertySchema(){
        return propertySchema;
    }
}