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
package jp.ossc.nimbus.service.test.action;

import java.io.BufferedReader;
import java.io.Reader;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.beans.PropertyFactory;
import jp.ossc.nimbus.beans.Property;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.ChainTestAction;
import jp.ossc.nimbus.service.test.TestContext;

/**
 * �I�u�W�F�N�g�̃v���p�e�B�l���擾����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class PropertyGetActionService extends ServiceBase implements TestAction, ChainTestAction.TestActionProcess, TestActionEstimation, PropertyGetActionServiceMBean{
    
    private static final long serialVersionUID = 7682175982096275432L;
    protected double expectedCost = 0d;
    
    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA�I�u�W�F�N�g�̃v���p�e�B�l���擾����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * property
     * targetObjectId
     * </pre>
     * property�́A�擾����v���p�e�B��������w�肷��B�v���p�e�B������́A{@link PropertyFactory#createProperty(String)}�ŉ��߂����B<br>
     * targetObjectId�́A�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return �擾�����v���p�e�B�l
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        return execute(context, actionId, null, resource);
    }
    
    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA�I�u�W�F�N�g�̃v���p�e�B�l���擾����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * property
     * targetObjectId
     * </pre>
     * property�́A�擾����v���p�e�B��������w�肷��B�v���p�e�B������́A{@link PropertyFactory#createProperty(String)}�ŉ��߂����B<br>
     * targetObjectId�́A�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��BpreResult���g�p����ꍇ�́A��s���w�肷��B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param preResult �v���p�e�B�̎擾�ΏۂƂȂ�I�u�W�F�N�g
     * @param resource ���\�[�X
     * @return �擾�����v���p�e�B�l
     */
    public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        Object targetObject = preResult;
        Property property = null;
        try{
            final String propStr = br.readLine();
            if(propStr == null || propStr.length() == 0){
                throw new Exception("Unexpected EOF on property");
            }
            property = PropertyFactory.createProperty(propStr);
            property.setIgnoreNullProperty(true);
            final String targetObjectId = br.readLine();
            if(targetObjectId != null && targetObjectId.length() != 0){
                Object actionResult = null;
                if(targetObjectId.indexOf(",") == -1){
                    actionResult = context.getTestActionResult(targetObjectId);
                }else{
                    String[] ids = targetObjectId.split(",");
                    if(ids.length != 2){
                        throw new Exception("Illegal targetObjectId format. id=" + targetObjectId);
                    }
                    actionResult = context.getTestActionResult(ids[0], ids[1]);
                }
                if(actionResult == null){
                    throw new Exception("TestActionResult not found. id=" + targetObjectId);
                }
                targetObject = actionResult;
            }
        }finally{
            br.close();
            br = null;
        }
        return property.getProperty(targetObject);
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
}
