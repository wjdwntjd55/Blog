package com.project.agreetotermsofuse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgreementViewModel : ViewModel() {

    private val _allAgreeList = MutableLiveData<MutableList<Boolean>>()
    val allAgreeList: MutableLiveData<MutableList<Boolean>> get() = _allAgreeList

    init {
        _allAgreeList.value = mutableListOf(false, false, false, false, false)
    }

    fun updateAllAgree(isChecked: Boolean) {
        _allAgreeList.value = _allAgreeList.value?.map { isChecked }?.toMutableList()
    }

    fun updateAgreeState(index: Int, isChecked: Boolean) {
        _allAgreeList.value?.let { list ->
            if (index in list.indices) {
                list[index] = isChecked

                // 1, 2, 3, 4번째 항목이 모두 true일 때 0번째 항목을 true로 설정
                list[0] = if (list[1] && list[2] && list[3] && list[4]) true else false

                _allAgreeList.postValue(list)
            }
        }
    }

    fun isAllAgreed(): Boolean {
        return _allAgreeList.value?.let { list ->
            list[1] && list[2] && list[3]
        } ?: false
    }

}