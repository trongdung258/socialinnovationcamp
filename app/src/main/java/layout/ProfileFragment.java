package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuu.socialinnovationcamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    
    private ImageView imgAvatar;
    private TextView txtName;
    private TextView txtBirthday;
    private TextView txtAddress;
    private TextView txtPhoneNumber;
    private TextView txtOcupation;
    private RecyclerView listDairy;
    

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initWigget(view);
        return view;
    }
    
    private void initWigget(View view)
    {
        imgAvatar = (ImageView)view.findViewById(R.id.img_avatar);
        txtName = (TextView)view.findViewById(R.id.txt_name);
        txtBirthday = (TextView)view.findViewById(R.id.txt_birthday);
        txtAddress = (TextView)view.findViewById(R.id.txt_dress);
        txtPhoneNumber = (TextView)view.findViewById(R.id.txt_phone_number);
        txtOcupation = (TextView)view.findViewById(R.id.txt_ocupation);
        listDairy = (RecyclerView)view.findViewById(R.id.list_diary);
    }

}
