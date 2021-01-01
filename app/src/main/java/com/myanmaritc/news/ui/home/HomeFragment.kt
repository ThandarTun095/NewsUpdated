package com.myanmaritc.news.ui.home


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmaritc.news.R
import com.myanmaritc.news.model.ArticlesItem
import com.myanmaritc.news.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.OnClickListener {

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeAdapter: HomeAdapter

    lateinit var searchView: SearchView
    lateinit var queryTextListener: SearchView.OnQueryTextListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeAdapter = HomeAdapter()

        observeViewModel()
        setupRecyclerView()

        //androidx
        swipeRefreshLayout.setOnRefreshListener {
            observeViewModel()
            setupRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    fun setupRecyclerView(){
        recyclerTopHeadlines.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        homeAdapter.setOnClickListener(this)
    }


    fun observeViewModel(){


        homeViewModel.getResult().observe(
            viewLifecycleOwner, Observer {
                homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
            }
        )

        homeViewModel.getLoading().observe(
            viewLifecycleOwner, Observer { isLoading ->
                loadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            }
        )

        homeViewModel.getErrorStatus().observe(
            viewLifecycleOwner, Observer {status ->
                if (status){
                    homeViewModel.getErrorMessage().observe(
                        viewLifecycleOwner, Observer {
                      errorMessage.text = it
                        }
                    )//errorMessage
                }
            }
        )//errorStatus

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadResult()
    }
    override fun onClick(article: ArticlesItem) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(article)
        view?.findNavController()?.navigate(directions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager


    if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName));

            searchView.queryHint = "Search News"

            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("TextChange: >>>>>>", query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.i("TextChange: >>>>>>", newText!!)
//                    homeViewModel.loadSearchResult(newText!!)
//                    homeViewModel.getsearchResult().observe(
//                        viewLifecycleOwner, Observer {
//                            homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
//                        }
//
//                    )
                //    setupRecyclerView()
                    return true
                }

            })
       //     searchView.setOnQueryTextListener(queryTextListener)
        }


    }



//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.action_search -> return false
//        }
//        searchView.setOnQueryTextListener(queryTextListener)
//        return super.onOptionsItemSelected(item)
//
//    }



}


//import android.app.SearchManager
//import android.content.Context
//import android.os.Bundle
//import android.view.*
//import android.widget.SearchView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//import com.myanmaritc.news.R
//import com.myanmaritc.news.model.ArticlesItem
//import kotlinx.android.synthetic.main.fragment_home.*
//
//
//class HomeFragment : Fragment() {
//
//    lateinit var homeViewModel: HomeViewModel
//    lateinit var homeAdapter: HomeAdapter
//
//    private lateinit var queryTextListener: SearchView.OnQueryTextListener
//
//    lateinit var searchView: SearchView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        homeAdapter = HomeAdapter()
//
//        observeViewModel()
//        setupRecyclerView()
//
//        //androidx
//        swipeRefreshLayout.setOnRefreshListener {
//            observeViewModel()
//            setupRecyclerView()
//            swipeRefreshLayout.isRefreshing = false
//        }
//
//    }
//
//    fun setupRecyclerView(){
//        recyclerTopHeadlines.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = homeAdapter
//        }
//    }
//
//    fun observeViewModel(){
//
//
//
//        homeViewModel.getResult().observe(
//            viewLifecycleOwner, Observer {
//                homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
//            }
//        )
//
//        homeViewModel.getLoading().observe(
//            viewLifecycleOwner, Observer { isLoading ->
//                loadingProgress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
//            }
//        )
//
//        homeViewModel.getErrorStatus().observe(
//            viewLifecycleOwner, Observer {status ->
//                if (status){
//                    homeViewModel.getErrorMessage().observe(
//                        viewLifecycleOwner, Observer {
//                      errorMessage.text = it
//                        }
//                    )//errorMessage
//                }
//            }
//        )//errorStatus
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        homeViewModel.loadResult()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.main_menu, menu)
//
//        val menuItem = menu.findItem(R.id.action_search)
//
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        searchView = menuItem.actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
//
//        searchView.queryHint = "Search news...."
//
//        queryTextListener = object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(query: String?): Boolean {
//                homeViewModel.loadSearchResult(query!!)
//                homeViewModel.getsearchResult().observe(
//                    viewLifecycleOwner, Observer {
//                        homeAdapter.updateArticleList(it.articles as List<ArticlesItem>)
//                    }
//                )
//                setupRecyclerView()
//                return true
//            }
//
//        }
//
//        searchView.setOnQueryTextListener(queryTextListener )
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.action_search -> return false
//        }
//        searchView.setOnQueryTextListener(queryTextListener)
//        return super.onOptionsItemSelected(item)
//
//    }
//
//
//}