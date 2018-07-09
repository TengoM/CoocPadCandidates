package com.cookpad.candidateinfo.ui.helper

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.cookpad.candidateinfo.R


object SwipeMenuListHelper {


    fun getMenuCreator(context: Context) = SwipeMenuCreator { menu ->
        val menuItems = getMenuItems(context)
        menuItems.forEach { menuItem ->
            menu.addMenuItem(menuItem)
        }
    }

    private fun getMenuItems(context: Context): List<SwipeMenuItem> {
        val menuFac = SwipeMenuItemFactory()
        return listOf(
                menuFac.getMenuItem(MenuItemType.DELETE, context),
                menuFac.getMenuItem(MenuItemType.EDIT, context)
        )
    }

    fun isActionDelete(index: Int) = index == 0

    private class SwipeMenuItemFactory {

        fun getMenuItem(type: MenuItemType, context: Context): SwipeMenuItem {
            return getMenuItem(type.value, context)
        }

        private fun getMenuItem(name: String, context: Context): SwipeMenuItem {
            val openItem = SwipeMenuItem(context)
            // set item background
            openItem.background = ColorDrawable(Color.rgb(0xC9, 0xC9,
                    0xCE))
            openItem.width = getMenuWidth(context)
            openItem.title = name
            openItem.titleSize = 18
            openItem.titleColor = Color.WHITE
            return openItem
        }

        private fun getMenuWidth(context: Context) = context.resources.getDimension(R.dimen.list_item_menu_width).toInt()


    }


    enum class MenuItemType(val value: String) {
        EDIT("edit"), DELETE("delete")
    }
}