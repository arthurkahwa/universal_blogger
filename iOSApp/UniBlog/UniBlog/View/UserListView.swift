//
//  UserListView.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import SwiftUI

struct UserListView: View {
    @EnvironmentObject var viewModel: ViewModel
    @Binding var selectedUser: UserPost?
    
    var body: some View {
        ZStack {
            if viewModel.userListLoading {
                ProgressView {
                    Text("Loading User List")
                        .font(.largeTitle)
                }
            }
            else {
                VStack(alignment: .leading) {
                    List(viewModel.userList,
                         id: \.self,
                         selection: $selectedUser) { user in
                        VStack {
                            Text(user.username)
                                .font(.title)
                        }
                    }
                }
            }
        }
        .task {
            await viewModel.findUserList()
        }
    }
}

struct UserListView_Previews: PreviewProvider {
    
    static var previews: some View {
        UserListView(selectedUser: .constant(UserPost(id: 32,
                                                      username: "preview user",
                                                      email: "some@mail.local",
                                                      posts: [])))
    }
}
