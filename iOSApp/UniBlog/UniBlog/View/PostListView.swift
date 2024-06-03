//
//  PostListView.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import SwiftUI

struct PostListView: View {
    @EnvironmentObject var viewModel: ViewModel
    @Binding var selectedUser: UserPost?
    @Binding var selectedPost: Post?
    
    var body: some View {
        ZStack {
            if let selectedUser = selectedUser {
                ZStack {
                    List(selectedUser.posts,
                         id: \.self,
                         selection: $selectedPost) { post in
                        Text(post.title)
                            .font(.title)
                    }
                }
            }
        }
    }
}

struct PostListView_Previews: PreviewProvider {
    static var previews: some View {
        PostListView(selectedUser: .constant(UserPost(id: 32,
                                                      username: "preview user",
                                                      email: "some@mail.local",
                                                      posts: [])),
                     selectedPost: .constant(Post(id: 1,
                                                  userID: 2,
                                                  title: "Preview Post",
                                                  body: "Preview Post Body"))
        )
    }
}
