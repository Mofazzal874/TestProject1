package com.example.testproject1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject1.HelperClasses.TeamMembersAdapter;
import com.example.testproject1.TeamMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeamTrackerFragment extends Fragment {

    private RecyclerView recyclerView;
    private TeamMembersAdapter adapter;
    private List<TeamMember> teamMembersList;
    private List<TeamMember> searchResults;

    private EditText searchEditText;
    private Button searchButton;

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_tracker, container, false);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        // Initialize lists
        teamMembersList = new ArrayList<>();
        searchResults = new ArrayList<>();

        // Initialize RecyclerView and adapter
        recyclerView = view.findViewById(R.id.recyclerViewTeamMembers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TeamMembersAdapter(teamMembersList, getContext());
        recyclerView.setAdapter(adapter);

        // Fetch team members from Firebase
        fetchTeamMembers();

        // Initialize search views
        searchEditText = view.findViewById(R.id.searchView);
        searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchEditText.getText().toString().toLowerCase().trim();
                searchUsers(searchQuery);
            }
        });

        return view;
    }

    private void fetchTeamMembers() {
        // Assuming your Firebase structure has a child "Users" containing team members
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teamMembersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TeamMember teamMember = snapshot.getValue(TeamMember.class);
                    teamMembersList.add(teamMember);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void searchUsers(String query) {
        searchResults.clear();

        for (TeamMember member : teamMembersList) {
            if (member.getUserName().toLowerCase().contains(query) || member.getEmail().toLowerCase().contains(query)) {
                searchResults.add(member);
            }
        }

        // Update RecyclerView with search results
        adapter.setTeamMembersList(searchResults);
        adapter.notifyDataSetChanged();
    }
}
