package com.example.notes.on_board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.data.local.pref.Pref
import com.example.notes.data.local.models.OnBoardModel
import com.example.notes.databinding.FragmentOnBoardBinding
import com.example.notes.on_board.adapter.OnBoardAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OnBoardAdapter(loadOnBoardData(), ::onStartBoard, ::onSkipBoard)
        binding.vpOnBoard.adapter = adapter

        binding.indicator.setViewPager(binding.vpOnBoard)
    }

    private fun onSkipBoard() {
        binding.vpOnBoard.currentItem = adapter.itemCount - 1
    }

    private fun onStartBoard() {
        pref.saveUserSeen(true)
        val pref = Pref(requireContext())
        pref.getUserSeen()

        findNavController().navigate(
            R.id.mainFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.onBoardFragment, true)
                .build()
        )
    }

    private fun loadOnBoardData(): List<OnBoardModel> {
        return listOf(
            OnBoardModel(
                gifRes = R.drawable.onboarding1,
                title = "Удобство",
                desc = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
            ),
            OnBoardModel(
                gifRes = R.drawable.onboarding2,
                title = "Организация",
                desc = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            ),
            OnBoardModel(
                gifRes = R.drawable.onboarding3,
                title = "Синхронизация",
                desc = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            )
        )
    }
}