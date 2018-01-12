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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �X�P�W���[�������������e�X�g���\�[�X�N���X�B<p>
 * 
 * @author M.Ishida
 */
public class ScheduledTestResourceImpl extends TestResourceBaseImpl implements ScheduledTestResource, java.io.Serializable {
    
    private static final long serialVersionUID = 2440747536475034151L;
    
    private String creator;
    private Date scheduledCreateStartDate;
    private Date scheduledCreateEndDate;
    private double expectedCost = 0d;
    private Date createStartDate;
    private Date createEndDate;
    private double cost = 0d;
    private double progress;
    private Map actionExpectedCostMap;
    private Map actionCostMap;
    
    public ScheduledTestResourceImpl(){
        super();
        actionExpectedCostMap = new LinkedHashMap();
        actionCostMap = new LinkedHashMap();
    }
    
    public String getCreator() {
        return creator;
    }
    
    /**
     * �쐬�҂�ݒ肷��B<p>
     *
     * @param creator �쐬��
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public Date getScheduledCreateStartDate() {
        return scheduledCreateStartDate;
    }
    
    /**
     * �쐬�J�n�\�������ݒ肷��B<p>
     *
     * @param date �쐬�J�n�\�����
     */
    public void setScheduledCreateStartDate(Date date) {
        scheduledCreateStartDate = date;
    }
    
    public Date getScheduledCreateEndDate() {
        return scheduledCreateEndDate;
    }
    
    /**
     * �쐬�I���\�������ݒ肷��B<p>
     *
     * @param date �쐬�I���\�����
     */
    public void setScheduledCreateEndDate(Date date) {
        scheduledCreateEndDate = date;
    }
    
    /**
     * �\��R�X�g��ݒ肷��B<p>
     *
     * @param cost �\��R�X�g
     */
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    /**
     * �\��R�X�g���擾����B<p>
     * �A�N�V�������̗\��R�X�g���w�肳��Ă���ꍇ�́A�A�N�V�����̗\��R�X�g�̑��a��Ԃ��B
     *
     * @return �\��R�X�g
     */
    public double getExpectedCost() {
        if (actionExpectedCostMap.isEmpty()) {
            return expectedCost;
        }
        Iterator itr = actionExpectedCostMap.values().iterator();
        double result = 0;
        while (itr.hasNext()) {
            double val = ((Double) itr.next()).doubleValue();
            if (!Double.isNaN(val)) {
                result += val;
            }
        }
        return result;
    }
    
    public Date getCreateStartDate() {
        return createStartDate;
    }
    
    /**
     * �쐬�J�n������ݒ肷��B<p>
     *
     * @param date �쐬�J�n����
     */
    public void setCreateStartDate(Date date) {
        createStartDate = date;
    }
    
    public Date getCreateEndDate() {
        return createEndDate;
    }
    
    /**
     * �쐬�I��������ݒ肷��B<p>
     *
     * @param date �쐬�I������
     */
    public void setCreateEndDate(Date date) {
        createEndDate = date;
    }
    
    public double getProgress() {
        return progress;
    }
    
    /**
     * �i������ݒ肷��B<p>
     *
     * @param progress �i����
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }
    
    /**
     * �R�X�g��ݒ肷��B<p>
     *
     * @param cost �R�X�g
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    /**
     * �R�X�g���擾����B<p>
     * �A�N�V�������̃R�X�g���w�肳��Ă���ꍇ�́A�A�N�V�����̃R�X�g�̑��a��Ԃ��B
     *
     * @return �R�X�g
     */
    public double getCost() {
        if (actionCostMap.isEmpty()) {
            return cost;
        }
        Iterator itr = actionCostMap.values().iterator();
        double result = 0;
        while (itr.hasNext()) {
            double val = ((Double) itr.next()).doubleValue();
            if (!Double.isNaN(val)) {
                result += val;
            }
        }
        return result;
    }
    
    public double getActionExpectedCost(String actionId) {
        return ((Double) actionExpectedCostMap.get(actionId)).doubleValue();
    }
    
    /**
     * �w�肳�ꂽ�A�N�V�����̗\��R�X�g��ݒ肷��B<p>
     *
     * @param actionId �A�N�V����ID
     * @param cost �\��R�X�g
     */
    public void setActionExpectedCost(String actionId, double cost) {
        actionExpectedCostMap.put(actionId, new Double(cost));
    }
    
    /**
     * �A�N�V�����̗\��R�X�g�}�b�v��ݒ肷��B<p>
     *
     * @return �A�N�V����ID�A�\��R�X�g�̃}�b�v
     */
    public Map getActionExpectedCostMap() {
        return actionExpectedCostMap;
    }
    
    public double getActionCost(String actionId) {
        return ((Double) actionCostMap.get(actionId)).doubleValue();
    }
    
    /**
     * �w�肳�ꂽ�A�N�V�����̃R�X�g��ݒ肷��B<p>
     *
     * @param actionId �A�N�V����ID
     * @param cost �R�X�g
     */
    public void setActionCost(String actionId, double cost) {
        if(!Double.isNaN(cost)){
            actionCostMap.put(actionId, new Double(cost));
        }
    }
    
    /**
     * �A�N�V�����̃R�X�g�}�b�v��ݒ肷��B<p>
     *
     * @return �A�N�V����ID�A�R�X�g�̃}�b�v
     */
    public Map getActionCostMap() {
        return actionCostMap;
    }
}
