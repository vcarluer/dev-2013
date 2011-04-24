using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace SpellExporter
{
    public partial class FormSpellExporter : Form
    {
        private Spell spell;        
        private IList<Spell> spells;

        public FormSpellExporter()
        {
            this.spells = new List<Spell>();
            InitializeComponent();
        }

        private void btParse_Click(object sender, EventArgs e)
        {
            spell = SpellExporterService.Parse(this.txtToParse.Text);
            this.ShowCurrentSpell();
        }

        private void ShowCurrentSpell()
        {
            if (spell != null)
            {
                this.txtName.Text = spell.Name;
                this.txtScool.Text = spell.School;
                this.txtRegister.Text = spell.Register;
                this.txtBranch.Text = spell.Branch;

                this.txtLvlMagEns.Text = spell.LevelMagician;
                this.txtLvlPriest.Text = spell.LevelPriest;
                this.txtLvlPal.Text = spell.LevelPaladin;
                this.txtLvlBard.Text = spell.LevelBard;
                this.txtLvlDruid.Text = spell.LevelDruid;
                this.txtLvlStriker.Text = spell.LevelStriker;

                this.txtCastingTime.Text = spell.CastingTime;
                this.txtComponents.Text = spell.Components;
                this.txtRange.Text = spell.Range;
                this.txtTargetEffectArea.Text = spell.TargetEffectArea;
                this.txtDuration.Text = spell.Duration;
                this.txtSavingThrow.Text = spell.SavingThrow;
                this.txtSpellResistance.Text = spell.SpellResistance;
                this.txtShortDescription.Text = spell.ShortDescription;
                this.txtDescription.Text = spell.Description;
            }
        }

        private void btIntegrate_Click(object sender, EventArgs e)
        {
            if (this.spell != null)
            {                
                if (!this.spells.Contains(this.spell))
                {
                    IEnumerable<Spell> sameName = from sp in this.spells where sp.Name == spell.Name select sp;
                    if (sameName.Count() == 0)
                    {
                        this.spells.Add(this.spell);
                    }
                    else
                    {
                        this.spell = sameName.First();                        
                    }
                }

                spell.Name = this.txtName.Text;
                spell.School = this.txtScool.Text;
                spell.Register = this.txtRegister.Text;
                spell.Branch = this.txtBranch.Text;

                spell.LevelMagician = this.txtLvlMagEns.Text;
                spell.LevelPriest = this.txtLvlPriest.Text;
                spell.LevelPaladin = this.txtLvlPal.Text;
                spell.LevelBard = this.txtLvlBard.Text;
                spell.LevelDruid = this.txtLvlDruid.Text;
                spell.LevelStriker = this.txtLvlStriker.Text;

                spell.CastingTime = this.txtCastingTime.Text;
                spell.Components = this.txtComponents.Text;
                spell.Range = this.txtRange.Text;
                spell.TargetEffectArea = this.txtTargetEffectArea.Text;
                spell.Duration = this.txtDuration.Text;
                spell.SavingThrow = this.txtSavingThrow.Text;
                spell.SpellResistance = this.txtSpellResistance.Text;
                spell.ShortDescription = this.txtShortDescription.Text;
                spell.Description = this.txtDescription.Text;

                this.dgSpells.DataSource = null;
                this.dgSpells.DataSource = this.spells;
            }
        }

        private void btParseList_Click(object sender, EventArgs e)
        {
            this.spells = SpellExporterService.ParseList(this.txtToParse.Text);

            this.dgSpells.DataSource = null;
            this.dgSpells.DataSource = this.spells;
        }

        private void btSave_Click(object sender, EventArgs e)
        {
            if (this.spells != null)
            {
                using (StreamWriter sw = new StreamWriter("export.txt"))
                {
                    foreach (Spell spell in this.spells)
                    {
                        string export = spell.SerializeForExport();
                        sw.WriteLine(export);
                    }
                }                               
            }
        }

        private void dgSpells_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            this.spell = this.spells[e.RowIndex];
            this.ShowCurrentSpell();
        }

        private void btClearAll_Click(object sender, EventArgs e)
        {
            this.spells = new List<Spell>();
            this.dgSpells.DataSource = null;
            this.dgSpells.DataSource = this.spells;
        }

        private void btParseListUnion_Click(object sender, EventArgs e)
        {
            this.spells = this.spells.Union(SpellExporterService.ParseList(this.txtToParse.Text)).ToList();

            this.dgSpells.DataSource = null;
            this.dgSpells.DataSource = this.spells;
        }

        private void btParseShortDescriptionList_Click(object sender, EventArgs e)
        {
            SpellExporterService.ParseShortDescriptionAndMerge(this.txtToParse.Text, this.spells);
        }

        private void btParseShortDescriptionFullList_Click(object sender, EventArgs e)
        {
            SpellExporterService.ParseShortDescriptionFullListAndMerge(this.txtToParse.Text, this.spells);
        }
    }
}
