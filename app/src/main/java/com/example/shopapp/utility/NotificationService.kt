package com.example.shopapp.utility

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_SOUND
import androidx.core.app.NotificationCompat.DEFAULT_VIBRATE
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.featureModules.authModule.di.DaggerAuthComponent
import com.example.shopapp.featureModules.authModule.models.FcmTokenModel
import com.example.shopapp.featureModules.authModule.viewmodels.AuthViewModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class NotificationService: FirebaseMessagingService() {

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var dataStoreManager: DataStoreManager


    override fun onCreate() {
        super.onCreate()

        authViewModel = AuthViewModel()
        DaggerAuthComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(authViewModel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val CHANNEL_ID = "HEADS_UP_NOTIFICATION"

        val title: String? = remoteMessage.notification?.title
        val text: String? = remoteMessage.notification?.body

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Heads Up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NotifChannel"
            val description = "NotifDescription"
            val importance =
                NotificationManager.IMPORTANCE_HIGH //Important for heads-up notification
            val channel = NotificationChannel("1", name, importance)
            channel.description = description
            channel.setShowBadge(true)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification: NotificationCompat
        .Builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(com.example.shopapp.R.drawable.shopicon)
            .setPriority(Notification.PRIORITY_MAX)
            .setDefaults(DEFAULT_SOUND or DEFAULT_VIBRATE)
            .setAutoCancel(false)




                val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(0, notification.build())



//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//
//
//            return
//        }
//        else{
//            NotificationManagerCompat.from(this).notify(1, notification.build())
//        }

        super.onMessageReceived(remoteMessage)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        val user = runBlocking { dataStoreManager.user.first() }

        val fcmTokenModel = FcmTokenModel(
            userId = user.id,
            fcmToken = token
        )

        authViewModel.setFcmToken(fcmTokenModel)

    }


}