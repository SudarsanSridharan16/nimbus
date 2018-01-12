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
package jp.ossc.nimbus.util.validator;

import java.util.*;

/**
 * �g�ݍ��킹�o���f�[�^�B<p>
 * �����̃o���f�[�^��_�����Z�q�ŘA�����Č��؂���B<br>
 * 
 * @author M.Takata
 */
public class CombinationValidator implements Validator, java.io.Serializable{
    
    private static final long serialVersionUID = 1695449609101701493L;
    
    /**
     * �g�ݍ��킹��ꂽ�o���f�[�^�Ƙ_�����Z�q�̃��X�g�B<p>
     * ValidatorWithCondition�̃��X�g�B<br>
     */
    protected List validators = new ArrayList();
    
    /**
     * �ŏ��̃o���f�[�^��ǉ�����B<p>
     *
     * @param validator �o���f�[�^
     * @exception ValidateException ���Ƀo���f�[�^���o�^����Ă���ꍇ
     */
    public void add(Validator validator) throws ValidateException{
        if(validators.size() != 0){
            throw new ValidateException("It is not the first validator.");
        }
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        validators.add(cond);
    }
    
    /**
     * �ŏ��̃o���f�[�^��NOT���Z�q�t���Œǉ�����B<p>
     *
     * @param validator �o���f�[�^
     * @exception ValidateException ���Ƀo���f�[�^���o�^����Ă���ꍇ
     */
    public void addNot(Validator validator) throws ValidateException{
        if(validators.size() != 0){
            throw new ValidateException("It is not the first validator.");
        }
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        cond.isNot = true;
        validators.add(cond);
    }
    
    /**
     * �o���f�[�^��OR���Z�q�t���ŘA������B<p>
     *
     * @param validator �o���f�[�^
     */
    public void or(Validator validator){
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        cond.isOr = true;
        validators.add(cond);
    }
    
    /**
     * �o���f�[�^��AND���Z�q�t���ŘA������B<p>
     *
     * @param validator �o���f�[�^
     */
    public void and(Validator validator){
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        cond.isAnd = true;
        validators.add(cond);
    }
    
    /**
     * �o���f�[�^��OR NOT���Z�q�t���ŘA������B<p>
     *
     * @param validator �o���f�[�^
     */
    public void orNot(Validator validator){
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        cond.isNot = true;
        cond.isOr = true;
        validators.add(cond);
    }
    
    /**
     * �o���f�[�^��AND NOT���Z�q�t���ŘA������B<p>
     *
     * @param validator �o���f�[�^
     */
    public void andNot(Validator validator){
        final ValidatorWithCondition cond
             = new ValidatorWithCondition(validator);
        cond.isNot = true;
        cond.isAnd = true;
        validators.add(cond);
    }
    
    /**
     * �o���f�[�^��S�č폜����B<p>
     */
    public void clear(){
        validators.clear();
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��_�����Z�q�ŘA�����ꂽ�o���f�[�^���g���Č��؂���B<p>
     *
     * @param obj ���ؑΏۂ̃I�u�W�F�N�g
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(Object obj) throws ValidateException{
        if(validators.size() == 0){
            return true;
        }
        Boolean result = null;
        for(int i = 0, imax = validators.size(); i < imax; i++){
            final ValidatorWithCondition cond
                 = (ValidatorWithCondition)validators.get(i);
            result = cond.validate(result, obj) ? Boolean.TRUE : Boolean.FALSE;
        }
        return result == null ? true : result.booleanValue();
    }
    
    /**
     * �_�����Z�q�t���o���f�[�^�B<p>
     *
     * @author M.Takata
     */
    protected static class ValidatorWithCondition
     implements java.io.Serializable{
        
        private static final long serialVersionUID = 924450733620787066L;
        
        /**
         * NOT���Z�q�t�����ǂ����̃t���O�B<p>
         */
        protected boolean isNot;
        
        /**
         * OR���Z�q�t�����ǂ����̃t���O�B<p>
         */
        protected boolean isOr;
        
        /**
         * AND���Z�q�t�����ǂ����̃t���O�B<p>
         */
        protected boolean isAnd;
        
        /**
         * �o���f�[�^�B<p>
         */
        protected Validator validator;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         *
         * @param validator �o���f�[�^
         */
        public ValidatorWithCondition(Validator validator){
            this.validator = validator;
        }
        
        /**
         * �w�肳�ꂽ�I�u�W�F�N�g���o���f�[�^�Ō��؂��A���̌��،��ʂƂ����܂ł̌��،��ʂ̘_�����Z���s���B<p>
         *
         * @param preResult �����܂ł̌��،���
         * @param obj ���ؑΏۂ̃I�u�W�F�N�g
         * @return ���،��ʁB���ؐ����̏ꍇtrue
         * @exception ValidateException ���؂Ɏ��s�����ꍇ
         */
        public boolean validate(Boolean preResult, Object obj)
         throws ValidateException{
            if(preResult != null){
                if(!preResult.booleanValue() && isAnd){
                    return false;
                }else if(preResult.booleanValue() && isOr){
                    return true;
                }
            }
            boolean result = validator.validate(obj);
            result = isNot ? !result : result;
            if(preResult != null){
                if(isOr){
                    result = result | preResult.booleanValue();
                }else if(isAnd){
                    result = result & preResult.booleanValue();
                }
            }
            return result;
        }
    }
}