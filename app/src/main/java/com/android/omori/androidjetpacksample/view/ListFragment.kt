package com.android.omori.androidjetpacksample.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.omori.androidjetpacksample.R
import com.android.omori.androidjetpacksample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        list_dog.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refresh_layout.setOnRefreshListener {
            list_dog.visibility = View.GONE
            text_view_list_error.visibility = View.GONE
            progress_bar_loading.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refresh_layout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dogLiveData ->
            dogLiveData?.let {
                list_dog.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogLiveData)
            }
        })

        viewModel.dogsLoadError.observe(this, Observer { isError ->
            isError?.let {
                text_view_list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar_loading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    text_view_list_error.visibility = View.GONE
                    list_dog.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSettings -> {
                view?.let { Navigation.findNavController(it).navigate(ListFragmentDirections.actionSettings()) }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
