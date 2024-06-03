//
//  PostDetailView.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import SwiftUI

struct PostDetailView: View {
    @Binding var selectedPost: Post?
    
    var body: some View {
        VStack (alignment: .center) {
            Text(selectedPost?.title ?? "")
                .lineLimit(nil)
                .multilineTextAlignment(.center)
                .font(.largeTitle)
                .padding()
            
            Text(selectedPost?.body ?? "")
                .lineLimit(nil)
                .multilineTextAlignment(.center)
                .font(.title3)
        }
    }
}

struct PostDetailView_Previews: PreviewProvider {
    static var previews: some View {
        PostDetailView(selectedPost: .constant(Post(id: 5,
                                                    userID: 6,
                                                    title: "Preview title",
                                                    body: "Preview body")))
    }
}
