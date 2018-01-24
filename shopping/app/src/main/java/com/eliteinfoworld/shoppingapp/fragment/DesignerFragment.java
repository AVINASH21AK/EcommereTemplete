package com.eliteinfoworld.shoppingapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.eliteinfoworld.shoppingapp.R;
import com.eliteinfoworld.shoppingapp.api.model.FragmentModel;
import com.eliteinfoworld.shoppingapp.api.model.FragProductModel;
import com.eliteinfoworld.shoppingapp.api.model.GroupModel;
import com.eliteinfoworld.shoppingapp.utils.App;

import java.util.ArrayList;


public class DesignerFragment extends Fragment {

    String TAG = "==DesignerFragment==";

    String strFid = "";

    View viewFragment;

    ArrayList<FragmentModel> arraylistFragmentModels = new ArrayList<FragmentModel>();
    ArrayList<FragProductModel> arrProductList;
    RecyclerView recyclerView;
    
    ExpandListAdapter ExpAdapter;
    ExpandableListView ExpandList;


    @SuppressLint("ValidFragment")
    public DesignerFragment(String strFid, ArrayList<FragmentModel> arr) {
        this.strFid = strFid;
        this.arraylistFragmentModels = arr;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.row_product_fragment, container, false);

        try {
            initialization();


            /*------------ Add Pull to refresh for calling api for particular strFid -----------------*/

            if (strFid.equalsIgnoreCase("1")) {
                App.showLog(TAG, "============ setUserVisibleHint  1 =============");

                String productid[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
                String productName[] = {"Aaaaaa", "Abbbbb", "Accc", "Adddd", "Baaaa", "Bbbbb", "Bcccc", "Bdddd", "C", "C", "C", "C", "D", "F", "G"};


                arrProductList = new ArrayList<FragProductModel>();
                for (int i = 0; i < productid.length; i++) {
                    arrProductList.add(new FragProductModel(productid[i], productName[i]));
                }

            } else if (strFid.equalsIgnoreCase("2")) {
                App.showLog(TAG, "============ setUserVisibleHint  2 =============");

                String productid[] = {"1"};
                String productName[] = {"A"};

                arrProductList = new ArrayList<FragProductModel>();
                for (int i = 0; i < productid.length; i++) {
                    arrProductList.add(new FragProductModel(productid[i], productName[i]));
                }

            } else if (strFid.equalsIgnoreCase("3")) {
                App.showLog(TAG, "============ setUserVisibleHint  3 =============");

                String productid[] = {"1", "2"};
                String productName[] = {"A", "B"};

                arrProductList = new ArrayList<FragProductModel>();
                for (int i = 0; i < productid.length; i++) {
                    arrProductList.add(new FragProductModel(productid[i], productName[i]));
                }
            }

            setAdapter(arrProductList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewFragment;
    }


    private void initialization() {
        try {
            recyclerView = (RecyclerView) viewFragment.findViewById(R.id.rvProduct);

            ExpandList = (ExpandableListView) viewFragment.findViewById(R.id.lvExp);
            ExpandList.setGroupIndicator(null);
            ExpandList.setChildIndicator(null);
            ExpandList.setChildDivider(null);
            ExpandList.setDivider(null);
            ExpandList.setDividerHeight(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setAdapter(ArrayList<FragProductModel> arrProductList) {
        try {

            if (arrProductList != null && arrProductList.size() > 0) {

                ArrayList<GroupModel> arrGroup = new ArrayList<GroupModel>();

                for (int i = 0; i < arrProductList.size(); i++) {

                    arrGroup.add(new GroupModel(arrProductList.get(i).id, String.valueOf(arrProductList.get(i).name.charAt(0)), arrProductList));

                    /*if(i > 0){

                        char A = arrProductList.get(i).name.charAt(0);
                        char B = arrProductList.get(i-1).name.charAt(0);

                        if(A != B){

                        }
                    }*/


                }

                ExpAdapter = new ExpandListAdapter(getActivity(), arrGroup);
                ExpandList.setAdapter(ExpAdapter);
                 /*---  by defualt Expand all groups -- click event work ---*/
                for(int i=0; i<arrGroup.size(); i++){
                    ExpandList.expandGroup(i);
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class ExpandListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private ArrayList<GroupModel> groups;

       

        public ExpandListAdapter(Context context, ArrayList<GroupModel> groups) {
            this.context = context;
            this.groups = groups;
            
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            ArrayList<FragProductModel> chList = groups.get(groupPosition).childModel;
            return chList.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            //final FragProductModel model = (FragProductModel) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.row_log_childview, null);
            }

           
            TextView glass_quantity = (TextView) convertView.findViewById(R.id.glass_quantity);
            View viewLast = convertView.findViewById(R.id.viewLast);


            FragProductModel model1 = groups.get(groupPosition).childModel.get(childPosition);

            glass_quantity.setText(model1.name);


            /*---  if child position is last than divider GONE ---*/
            if(isLastChild == true){
                viewLast.setVisibility(View.VISIBLE);
            }else {
                viewLast.setVisibility(View.GONE);
            }




            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<FragProductModel> chList = groups.get(groupPosition).childModel;
            return chList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


            /*----- Expand all groups -- click event not working ----*/
            //ExpandList.expandGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(R.layout.row_log_groupview, null);
            }

            TextView glass_total_ml = (TextView) convertView.findViewById(R.id.glass_total_ml);

            final GroupModel group = (GroupModel) getGroup(groupPosition);
            glass_total_ml.setText(group.name);

            

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }



}