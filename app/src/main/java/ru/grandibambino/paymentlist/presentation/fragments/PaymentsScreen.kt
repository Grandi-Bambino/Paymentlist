package ru.grandibambino.paymentlist.presentation.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import ru.grandibambino.paymentlist.R
import ru.grandibambino.paymentlist.data.DataResult
import ru.grandibambino.paymentlist.data.Payment
import ru.grandibambino.paymentlist.presentation.PaymentAdapter
import ru.grandibambino.paymentlist.utils.navigate
import ru.grandibambino.paymentlist.utils.toast

class PaymentsScreen : BaseFragment(R.layout.fragment_playments_screen) {

    private val viewModel by viewModel<PaymentsViewModel>()

    private lateinit var adapter: PaymentAdapter
    private lateinit var paymentsRV: RecyclerView

    override fun onStart() {
        super.onStart()
        initFields(rootView)
        initRecyclerView()
        viewModel.getAllPayments()
        getAllPayments()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.dialog_logout))
                    .setPositiveButton(
                        getString(R.string.logout_positive_btn)
                    ) { _, _ -> navigate().navigate(R.id.authScreen) }
                    .setNegativeButton(
                        getString(R.string.logout_negative_btn)
                    ) { dialog, _ -> dialog.dismiss() }
                    .show()
                true
            }
            else -> false
        }
    }

    private fun getAllPayments() {
        viewModel.paymentsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    adapter.setData(it.data as MutableList<Payment>)
                }
                is DataResult.Error -> {
                    toast(getString(R.string.error_get_data_title))
                }
            }
        }
    }

    private fun initFields(rootView: View) {
        paymentsRV = rootView.findViewById(R.id.payments_rv)
        adapter = PaymentAdapter()
    }

    private fun initRecyclerView() {
        paymentsRV.adapter = adapter
    }


}