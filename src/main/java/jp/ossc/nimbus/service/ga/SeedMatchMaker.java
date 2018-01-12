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
package jp.ossc.nimbus.service.ga;

import java.util.Random;

/**
 * �V�[�h���l�B<p>
 *
 * @author M.Takata
 */
public interface SeedMatchMaker{
    
    /**
     * �����ΏۂƂȂ�Q�̃V�[�h��I�o����B<p>
     *
     * @param random �����V�[�h
     * @param generation ����
     * @param index ���݂̌�����
     * @param result �����Ώی��ʁB�ė��p�y�ьp�������p�̃o�P�b�g�Ƃ��Ĉ�����
     * @return �����ΏۑI�o����
     */
    public MatchMakeResult matchMake(Random random, Generation generation, int index, MatchMakeResult result);
    
    /**
     * �����ΏۑI�o���ʁB<p>
     *
     * @author M.Takata
     */
    public interface MatchMakeResult{
        
        /**
         * �����ΏۂƂ��đI�΂ꂽ�V�[�h�z����擾����B<p>
         *
         * @return �����ΏۂƂ��đI�΂ꂽ�V�[�h�z��B�����������ɂ��̂܂܂̃V�[�h���c���ꍇ�́A�v�f0�Ɏc���V�[�h�A�v�f1��null���i�[���ĕԂ��B�����Ώۂ��I���ł��Ȃ��ꍇ�́Anull��Ԃ��B
         */
        public Seed[] getPair();
    }
}