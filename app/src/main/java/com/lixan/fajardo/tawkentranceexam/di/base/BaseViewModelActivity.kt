package com.lixan.fajardo.tawkentranceexam.di.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.lixan.fajardo.tawkentranceexam.ViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


abstract class BaseViewModelActivity<BINDING: ViewDataBinding, VM: BaseViewModel> : BaseActivity<BINDING>() {

    @Inject
    lateinit var vmFactory: ViewModelFactory<VM>

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val vmClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>

        viewModel = ViewModelProviders.of(this, vmFactory).get(vmClass)
        viewModel.onCreate(intent.extras)
    }

}