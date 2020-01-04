package com.example.user.ui.main_screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user.main.BaseFragment
import com.example.user.R
import com.example.user.objects.UserInfo
import com.example.user.ui.extensions.setUpPagination
import kotlinx.android.synthetic.main.fragment_main_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<MainScreenViewModel>(R.layout.fragment_main_screen) {

    override val viewModel: MainScreenViewModel by viewModel()

    private val adapter: UserAdapter by lazy {
        UserAdapter { openDetailScreen(it) }
    }

    private fun openDetailScreen(it: UserInfo) {
        findNavController().navigate(MainScreenFragmentDirections.actionMainFragmentToDetailFragment(it))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getElements()
    }

    override fun subscribeToEvents() {
        super.subscribeToEvents()
        viewModel.showMainScreenUsers.observe(this, Observer { addData(it.first, it.second) })
    }


    private fun initRecyclerView() {
        main_screen_recycler.layoutManager = LinearLayoutManager(requireContext())
        main_screen_recycler.setHasFixedSize(true)
        main_screen_recycler.adapter = adapter
        main_screen_recycler.setUpPagination {
            viewModel.getElements(true)
        }
    }

    override fun showLoading(isLoading: Boolean) {
        main_progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun addData(list: List<UserInfo>, update: Boolean) {
        adapter.addItem(list, update)
    }


}