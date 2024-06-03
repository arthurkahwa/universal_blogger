//
//  ViewModel.swift
//  UniBlog
//
//  Created by Arthur Nsereko Kahwa on 02.07.23.
//

import Foundation

@MainActor
/// The main business logic used in the views
class ViewModel: ObservableObject {
    @Published var userList:[UserPost] = []
    @Published var userListLoading = true
    
    let service = Service.shared
    
    /// Get the list of users and their posts from the REST resource through the service
    func findUserList() async {
        let path = "http://localhost:8080/api/users/posts"
        let method = HttpMethod.GET
        
        let fetchUserResult: Result<[UserPost], AppError> = await service.DataOperation(on: path, using: method)
        switch fetchUserResult {
            case .success(let list):
                userList = list
                userListLoading = false
                
            case .failure(let error):
                    print("\(error)")
        }
    }
}
