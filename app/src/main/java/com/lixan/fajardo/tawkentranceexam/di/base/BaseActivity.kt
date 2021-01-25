package com.lixan.fajardo.tawkentranceexam.di.base

import android.os.Bundle
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.utils.SNACKBAR_MAX_LINES
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<BINDING: ViewDataBinding>: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var binding: BINDING

    protected val disposables: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var schedulers: BaseSchedulerProvider

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    fun showErrorSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view
        val tvSnackbar: TextView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text)
        tvSnackbar.maxLines = SNACKBAR_MAX_LINES
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.torch_red))
        snackbar.show()
    }

    fun showSuccessSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.sushi))
        snackbar.show()
    }
}