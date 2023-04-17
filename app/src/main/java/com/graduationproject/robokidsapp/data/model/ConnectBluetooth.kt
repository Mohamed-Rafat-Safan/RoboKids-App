package com.graduationproject.robokidsapp.data.model

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.*

class ConnectBluetooth(val context: Context) {

    var address: String? = null
    var name: String? = null

    var myBluetooth: BluetoothAdapter? = null
    var btSocket: BluetoothSocket? = null
    var pairedDevices: Set<BluetoothDevice>? = null
    val myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")



    fun bluetooth_connect_device() {
        try {
            myBluetooth = BluetoothAdapter.getDefaultAdapter()
            address = myBluetooth?.address

            // check Permission
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ){}

            pairedDevices = myBluetooth?.bondedDevices

            if (pairedDevices?.size!! > 0) {
                for (bt in pairedDevices!!) {
                    address = bt.address.toString()
                    name = bt.name.toString()
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
                }
            }

            myBluetooth = BluetoothAdapter.getDefaultAdapter() //get the mobile bluetooth device
            val dispositivo: BluetoothDevice =
                myBluetooth?.getRemoteDevice(address)!! //connects to the device's address and checks if it's available
            btSocket =
                dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID) //create a RFCOMM (SPP) connection

            btSocket?.connect()

        } catch (we: java.lang.Exception) {
        }
    }


    fun led_on_off(i: String) {
        try {
            if (btSocket != null) {
                btSocket!!.outputStream.write(i.toByteArray())
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}