using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

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
            spell = SpellExpoterService.Parse(this.txtToParse.Text);
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
                this.txtDescription.Text = spell.Description;
            }
        }

        private void btIntegrate_Click(object sender, EventArgs e)
        {
            if (this.spell != null)
            {
                this.spells.Add(this.spell);

                this.dgSpells.DataSource = this.spells;
            }
        }

        private void btParseList_Click(object sender, EventArgs e)
        {
            this.spells = SpellExpoterService.ParseList(this.txtToParse.Text);

            this.dgSpells.DataSource = this.spells;
        }
    }
}
