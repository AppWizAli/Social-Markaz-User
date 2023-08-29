
import android.os.Parcelable
import com.google.firebase.Timestamp


    data class User @JvmOverloads constructor(
        var email: String = "",
        var firstName: String = "",
        var gender: String = "",
        var address: String = "",
        var phone: String = "",
        var status: String = "",
        var photo: String = "",
        var cnic_front: String = "",
        var cnic_back: String = "",
        var pin: String = "",
        var user_id: String = "",
        var so_id: String = "",
         val createdAt: Timestamp = Timestamp.now() // Creation timestamp
)