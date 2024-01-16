package com.example.testproject1.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject1.R;
import com.example.testproject1.TeamMember;

import java.util.List;

public class TeamMembersAdapter extends RecyclerView.Adapter<TeamMembersAdapter.ViewHolder> {

    private List<TeamMember> teamMembers;
    private Context context;

    public TeamMembersAdapter(List<TeamMember> teamMembers, Context context) {
        this.teamMembers = teamMembers;
        this.context = context;
    }
    public void setTeamMembersList(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamMember teamMember = teamMembers.get(position);

        holder.memberName.setText(teamMember.getName());
        holder.userNameValue.setText(teamMember.getUserName());
        holder.phoneValue.setText(teamMember.getPhone());
        holder.bloodGroupValue.setText(teamMember.getBloodGroup());
        holder.addressValue.setText(teamMember.getAddress());

        // Load image using Glide
        Glide.with(context)
                .load(teamMember.getImageUrl())
                .placeholder(R.drawable.img_13)
                .error(R.drawable.__icon__image_)
                .into(holder.memberImage);

        // You can add more operations as needed
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView memberName, userNameValue, phoneValue, bloodGroupValue, addressValue;
        ImageView memberImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            memberName = itemView.findViewById(R.id.wishlist_title);
            userNameValue = itemView.findViewById(R.id.userNameValue);
            phoneValue = itemView.findViewById(R.id.phoneValue);
            bloodGroupValue = itemView.findViewById(R.id.bloodGroupValue);
            addressValue = itemView.findViewById(R.id.addressValue);
            memberImage = itemView.findViewById(R.id.wishlist_image);
        }
    }
}
