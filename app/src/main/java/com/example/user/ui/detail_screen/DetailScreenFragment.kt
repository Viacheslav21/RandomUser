package com.example.user.ui.detail_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.user.R
import com.example.user.main.BaseFragment
import com.example.user.objects.UserInfo
import kotlinx.android.synthetic.main.fragment_detail_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailScreenFragment : BaseFragment<DetailScreenViewModel>(R.layout.fragment_detail_screen) {

    override val viewModel: DetailScreenViewModel by viewModel()
    private val args by navArgs<DetailScreenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(args.user)
    }

    private fun initView(user: UserInfo) {
        Glide.with(requireContext())
            .load(user.picture?.large)
            .apply(RequestOptions.circleCropTransform())
            .into(detail_user_screen_image)

        user_detail_name.text = user.name?.getFullName()
        user_detail_age.text = getString(R.string.age, user.dob?.age.toString())
        user_detail_phone.setText(user.phone)

        user_detail_phone.setOnClickListener {
            makeCall(user.phone)
        }
        user_detail_gender.setText(user.gender)
        user_detail_email.setText(user.email)
        user_detail_dob.setText(user.dob?.getDob())
    }

    private fun makeCall(phone: String?) {
        if (!phone.isNullOrBlank()) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            requireActivity().startActivity(intent)
        }
    }

}