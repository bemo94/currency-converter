//
//  ViewController.swift
//  iosApp
//
//  Created by BENNIS MOHAMED on 09/05/2019.
//  Copyright © 2019 Octo. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController {
    
    @IBOutlet weak var txt: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        txt.text = Txt.init().getTxt()
    }
}
