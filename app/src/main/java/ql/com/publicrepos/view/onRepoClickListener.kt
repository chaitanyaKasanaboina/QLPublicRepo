package ql.com.publicrepos.view

import ql.com.publicrepos.model.RepoDetails

interface onRepoClickListener {
    fun onItemClick(repoDetails: RepoDetails)
}