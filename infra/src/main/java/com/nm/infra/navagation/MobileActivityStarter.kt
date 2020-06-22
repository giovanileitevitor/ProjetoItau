package com.nm.infra.navagation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Default request code used to start an activity for result.
 *
 * When this value is used, onActivityResult() will not be called.
 */
const val IGNORE_ACTIVITY_RESULT = -1

class MobileActivityStarter {

    companion object {
        @JvmOverloads
        @Throws(RuntimeException::class)
        fun activityStarter(
            fragment: Fragment,
            activityName: ActivityValues,
            bundle: Bundle = Bundle(),
            requestCode: Int = IGNORE_ACTIVITY_RESULT
        ) {
            startForResult(activityName, bundle) {
                fragment.startActivityForResult(it, requestCode)
            }
        }

        @JvmOverloads
        @Throws(RuntimeException::class)
        fun activityStarter(
            activity: Activity,
            activityActions: ActivityValues,
            bundle: Bundle = Bundle(),
            requestCode: Int = IGNORE_ACTIVITY_RESULT
        ) {
            startForResult(activityActions, bundle) {
                activity.startActivityForResult(it, requestCode)
            }
        }


        private fun startForResult(
            activityActions: ActivityValues,
            bundle: Bundle,
            completion: (intent: Intent) -> Unit
        ) {
            try {
                val intent = Intent(activityActions.value)
                intent.putExtras(bundle)
                completion.invoke(intent)
            } catch (e: Throwable) {
                val msg = "Não foi possível abrir $activityActions - Flavor errado?"
                throw RuntimeException(msg, e)
            }
        }
    }
}