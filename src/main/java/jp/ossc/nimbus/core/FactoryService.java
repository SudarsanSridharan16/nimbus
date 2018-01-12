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
package jp.ossc.nimbus.core;

/**
 * �t�@�N�g���T�[�r�X�C���^�t�F�[�X�B<p>
 * �T�[�r�X��񋟂���I�u�W�F�N�g�𐶐�����t�@�N�g���C���^�t�F�[�X�ł���B<p>
 * {@link Service}�́A�C���X�^���X���P����{@link ServiceManager}�ɓo�^���āA�����̃I�u�W�F�N�g����P�̃C���X�^���X���Q�Ƃ���Ďg����T�[�r�X���`���邽�߂̃C���^�t�F�[�X�ł���B<br>
 * ����ɑ΂��āA���̃C���^�t�F�[�X�́A�C���X�^���X���P����ServiceManager�ɓo�^����Ƃ���͓����ł��邪�A�T�[�r�X���s���I�u�W�F�N�g�́AServiceManager����擾���鎞�ɁA{@link #newInstance()}�ɂ���Ė��񐶐������ʂ̃I�u�W�F�N�g�ƂȂ�B�]���āA�T�[�r�X���g�����́A���ꂼ��قȂ�I�u�W�F�N�g���g�p���鎖�ɂȂ�B<br>
 * ����́A�Ⴆ�΁A�T�[�r�X��`�͂P�����ŁA���̎��̂ƂȂ�T�[�r�X�͕����g�p�������ꍇ�Ȃǂɗp����B<br>
 * <p>
 * ���̃t�@�N�g���ɂ���Đ������ꂽ�I�u�W�F�N�g�́A�T�[�r�X�Ƃ��ēo�^����Ȃ��B�]���āA���̂܂܂ł͂P�x��������Ă��܂����I�u�W�F�N�g�́A���̃t�@�N�g������؂藣����邽�߁A���̃t�@�N�g���̑�����ύX���Ă��A���f����邱�Ƃ͂Ȃ��B<br>
 * �������A�ꍇ�ɂ���ẮA���̃t�@�N�g���̑�����ς��鎖�ŁA���ɐ������ꂽ�I�u�W�F�N�g�̑�����ς������ꍇ������B���̃t�@�N�g���́A���������I�u�W�F�N�g���Ǘ�����@�\�������A{@link #setManagement(boolean)}�ɂ���Ă��̋@�\��ON/OFF�𐧌�\�ł���B<br>
 * �A���A���ӂ��K�v�Ȃ̂́AsetManagement(true)�ɂ��āA���������I�u�W�F�N�g�́A���̃t�@�N�g�����ŊǗ�����邽�߁A�g�������g���̂ĂĂ��A���̃t�@�N�g���T�[�r�X���j������Ȃ�����A�K�x�[�W����Ȃ��B<br>
 * �]���āA�t�@�N�g���ɊǗ����ꂽ�I�u�W�F�N�g���g�����́A�g���̂ĂŎg�p���ׂ��ł͂Ȃ��B�g���̂ĂŎg�p�������ꍇ�́AsetManagement(false)�ɂ��āA�Ǘ�����Ȃ��I�u�W�F�N�g�Ƃ��Ďg�p���ׂ��ł���B<br>
 * 
 * @author M.Takata
 * @see Service
 */
public interface FactoryService{
    
    /**
     * ���̃t�@�N�g������������I�u�W�F�N�g���Ǘ����邩�ǂ�����ݒ肷��B<p>
     * true�ɂ����ꍇ�A���̌�A���̃t�@�N�g���ɂ���Đ��������I�u�W�F�N�g�́A���̃t�@�N�g���̊Ǘ����ɒu�����B�Ǘ�����Ă���I�u�W�F�N�g�́A���̃t�@�N�g���̑����̕ύX�𔽉f�����B�i�ǂ̂悤�ȑ������Ǘ�����邩�́A�����Ɉˑ�����j���̂��߁A���̃t�@�N�g�����Q�Ƃ�ێ�����̂ŁA�g�������Q�Ƃ��̂ĂĂ��A�K�x�[�W����Ȃ��B���̃t�@�N�g�����A�j�����ꂽ�ꍇ�ɂ́A�Ǘ�����Ă���I�u�W�F�N�g�̎Q�Ƃ��j�������B<br>
     * false�ɂ����ꍇ�A���̌�A���̃t�@�N�g���ɂ���Đ��������I�u�W�F�N�g�́A���̃t�@�N�g���̊Ǘ����ɒu����Ȃ��B�Ǘ�����Ă��Ȃ��I�u�W�F�N�g�́A���̃t�@�N�g���̑����̕ύX�𔽉f����Ȃ��B���̂��߁A���̃t�@�N�g�����Q�Ƃ�ێ����邱�Ƃ͂Ȃ��̂ŁA�g�������Q�Ƃ��̂Ă�ƁA�K�x�[�W�̑ΏۂɂȂ�B<br>
     *
     * @param isManaged �Ǘ�����ꍇ��true�A�Ǘ����Ȃ��ꍇ��false
     * @see #isManagement()
     */
    public void setManagement(boolean isManaged);
    
    /**
     * ���̃t�@�N�g���ɂ���āA���̌�ɐ�������I�u�W�F�N�g���A�Ǘ�����邩�ǂ����𒲂ׂ�B<b>
     *
     * @return �Ǘ�����ꍇ��true�A�Ǘ����Ȃ��ꍇ��false
     * @see #setManagement(boolean)
     */
    public boolean isManagement();
    
    /**
     * ���̃t�@�N�g������������I�u�W�F�N�g���X���b�h�P�ʂɐ������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�B
     *
     * @param isThreadLocal �Ǘ�����ꍇ��true�A�Ǘ����Ȃ��ꍇ��false
     * @see #isThreadLocal()
     */
    public void setThreadLocal(boolean isThreadLocal);
    
    /**
     * ���̃t�@�N�g������������I�u�W�F�N�g���X���b�h�P�ʂɐ������邩�ǂ����𒲂ׂ�B<b>
     *
     * @return �X���b�h�P�ʂɐ�������ꍇ��true�A�X���b�h�P�ʂɐ������Ȃ��ꍇ��false
     * @see #setThreadLocal(boolean)
     */
    public boolean isThreadLocal();
    
    /**
     * ���̃t�@�N�g�����������Ǘ����Ă���I�u�W�F�N�g��j������B<p>
     *
     * @param service �j������T�[�r�X�I�u�W�F�N�g
     */
    public void release(Object service);
    
    /**
     * ���̃t�@�N�g�����������Ǘ����Ă���I�u�W�F�N�g��S�Ĕj������B<p>
     */
    public void release();
    
    /**
     * �T�[�r�X��񋟂���I�u�W�F�N�g�𐶐�����B<p>
     * {@link #isManagement()}��true�̏�ԂŁA���̃��\�b�h���Ăяo���ƁA���������I�u�W�F�N�g�́A���̃t�@�N�g���ɂ���ĊǗ�����A�t�@�N�g���̑����ύX�����f�����B<br>
     * isManagement()��false�̏�ԂŁA���̃��\�b�h���Ăяo���ƁA���������I�u�W�F�N�g�́A���̃t�@�N�g���ɂ���ĊǗ�����Ȃ����߁A�t�@�N�g���̑����ύX�͔��f����Ȃ��B�g���̂ẴI�u�W�F�N�g�ł���B<br>
     *
     * @return �T�[�r�X��񋟂���I�u�W�F�N�g
     * @see #setManagement(boolean)
     */
    public Object newInstance();
}