package ql.com.publicrepos.view

import ql.com.publicrepos.model.RepoDetails

interface OnItemClickListener {
    fun onItemClick(repoDetails: RepoDetails)
}