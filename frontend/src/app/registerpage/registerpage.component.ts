import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RequestEngine } from '../mainsv/requestengine';

@Component({
    selector: 'app-registerpage',
    templateUrl: './registerpage.component.html',
    styleUrls: ['./registerpage.component.scss'],
    standalone: true,
    imports: [
        MatButtonModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
        CommonModule,
    ]
})
export class RegisterpageComponent {

    name: string = '';
    surname: string = '';
    username: string = '';
    password: string = '';
    password_retyped: string = '';
    
    constructor(protected readonly reqeng: RequestEngine) {}
    
    register() {
        if(this.password != this.password_retyped) {
            alert("Hasła muszą być takie same!");
            return;
        }
        this.reqeng.registerUser({
            name: this.name,
            surname: this.surname,
            username: this.username,
            password: this.password
        }).subscribe(() => {window.location.href = "/login";});
    }

}
