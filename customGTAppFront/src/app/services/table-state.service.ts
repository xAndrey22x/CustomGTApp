import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TableStateService {
  private selectedTable: string = 'products';

  setSelectedTable(table: string) {
    this.selectedTable = table;
  }

  getSelectedTable(): string {
    return this.selectedTable;
  }
}
