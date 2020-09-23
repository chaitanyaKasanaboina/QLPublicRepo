package ql.com.publicrepos.view

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.repo_activity.*
import ql.com.publicrepos.R
import ql.com.publicrepos.model.RepoDetails
import ql.com.publicrepos.util.Constants
import ql.com.publicrepos.view.adapters.RepoRecyclerViewAdapter
import ql.com.publicrepos.viewmodel.RepoViewModel
import ql.com.publicrepos.viewmodel.ViewModelFactory
import javax.inject.Inject


class RepoActivity : AppCompatActivity(), onRepoClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var repoDetailsList: MutableList<RepoDetails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.repo_activity)
        val manager = LinearLayoutManager(this)
        repos_recycler_view.layoutManager = manager
        repos_recycler_view.setHasFixedSize(true)
        repos_recycler_view.layoutManager = manager
        val itemDecor = DividerItemDecoration(this, HORIZONTAL)
        repos_recycler_view.addItemDecoration(itemDecor)
        val repoRecyclerViewAdapter = RepoRecyclerViewAdapter(repoDetailsList, this)
        val repoViewModel = ViewModelProvider(this, viewModelFactory).get(RepoViewModel::class.java)
        repoViewModel.repoDetailsLiveData.observe(this, Observer {
            repoDetailsList.addAll(it)
            repoRecyclerViewAdapter.notifyDataSetChanged()
        })
        repos_recycler_view.adapter = repoRecyclerViewAdapter
    }

    override fun onItemClick(repoDetails: RepoDetails) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra(Constants.URL, repoDetails.htmlUrl)
        intent.putExtra(Constants.NAME, repoDetails.name)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logoff_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_logoff) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            //Complete and destroy RepoActivity activity once successful
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}