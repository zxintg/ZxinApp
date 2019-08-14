/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Volumes/android/6.0.1_viennalte/vendor/nextev/packages/apps/NextevHome/src/com/nextev/home/aidl/IHomeNaviAidl.aidl
 */
package com.zxin.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Receive Navi route plan, ongoing guide infomation & navigation operation
 *
 * 跨进程间通讯
 */
public interface IHomeNaviAidl extends IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends Binder implements IHomeNaviAidl {
        private static final String DESCRIPTOR = "com.nextev.home.aidl.IHomeNaviAidl";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.nextev.home.aidl.IHomeNaviAidl interface,
         * generating a proxy if needed.
         */
        public static IHomeNaviAidl asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IHomeNaviAidl))) {
                return ((IHomeNaviAidl) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_naviTransmit: {
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg0;
                    _arg0 = data.readStrongBinder();
                    String _arg1;
                    _arg1 = data.readString();
                    String _result = this.naviTransmit(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IHomeNaviAidl {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * naviInfo
             * -- Json data format
             */
            @Override
            public String naviTransmit(IBinder token, String naviInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(naviInfo);
                    mRemote.transact(Stub.TRANSACTION_naviTransmit, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_naviTransmit = (IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    /**
     * naviInfo
     * -- Json data format
     */
    public String naviTransmit(IBinder token, String naviInfo) throws RemoteException;
}
